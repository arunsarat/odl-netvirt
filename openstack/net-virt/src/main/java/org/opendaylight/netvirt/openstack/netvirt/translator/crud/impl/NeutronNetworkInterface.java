/*
 * Copyright (c) 2013, 2015 IBM Corporation and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.netvirt.openstack.netvirt.translator.crud.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.sal.binding.api.BindingAwareBroker.ProviderContext;
import org.opendaylight.netvirt.openstack.netvirt.translator.NeutronNetwork;
import org.opendaylight.netvirt.openstack.netvirt.translator.crud.INeutronNetworkCRUD;
import org.opendaylight.netvirt.openstack.netvirt.translator.NeutronNetwork_Segment;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.l3.ext.rev150712.NetworkL3Extension;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.l3.ext.rev150712.NetworkL3ExtensionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.NetworkTypeBase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.NetworkTypeFlat;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.NetworkTypeGre;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.NetworkTypeVlan;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.NetworkTypeVxlan;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.networks.attributes.Networks;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.networks.attributes.networks.Network;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.networks.rev150712.networks.attributes.networks.NetworkBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.provider.ext.rev150712.NetworkProviderExtension;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.provider.ext.rev150712.NetworkProviderExtensionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.provider.ext.rev150712.neutron.networks.network.Segments;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.provider.ext.rev150712.neutron.networks.network.SegmentsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.neutron.rev150712.Neutron;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableBiMap;

public class NeutronNetworkInterface extends AbstractNeutronInterface<Network,NeutronNetwork> implements INeutronNetworkCRUD {
    private static final Logger LOGGER = LoggerFactory.getLogger(NeutronNetworkInterface.class);

    private static final ImmutableBiMap<Class<? extends NetworkTypeBase>,String> NETWORK_MAP
            = new ImmutableBiMap.Builder<Class<? extends NetworkTypeBase>,String>()
            .put(NetworkTypeFlat.class,"flat")
            .put(NetworkTypeGre.class,"gre")
            .put(NetworkTypeVlan.class,"vlan")
            .put(NetworkTypeVxlan.class,"vxlan")
            .build();

    NeutronNetworkInterface(final DataBroker dataBroker) {
        super(dataBroker);
    }

    // IfNBNetworkCRUD methods

    @Override
    public boolean networkExists(String uuid) {
        Network network = readMd(createInstanceIdentifier(toMd(uuid)));
        if (network == null) {
            return false;
        }
        return true;
    }

    @Override
    public NeutronNetwork getNetwork(String uuid) {
        Network network = readMd(createInstanceIdentifier(toMd(uuid)));
        if (network == null) {
            return null;
        }
        return fromMd(network);
    }

    @Override
    public List<NeutronNetwork> getAllNetworks() {
        Set<NeutronNetwork> allNetworks = new HashSet<>();
        Networks networks = readMd(createInstanceIdentifier());
        if (networks != null) {
            for (Network network: networks.getNetwork()) {
                allNetworks.add(fromMd(network));
            }
        }
        LOGGER.debug("Exiting getAllNetworks, Found {} OpenStackNetworks", allNetworks.size());
        List<NeutronNetwork> ans = new ArrayList<>();
        ans.addAll(allNetworks);
        return ans;
    }

    @Override
    public boolean addNetwork(NeutronNetwork input) {
        if (networkExists(input.getID())) {
            return false;
        }
        addMd(input);
        return true;
    }

    @Override
    public boolean removeNetwork(String uuid) {
        if (!networkExists(uuid)) {
            return false;
        }
        return removeMd(toMd(uuid));
    }

    @Override
    public boolean updateNetwork(String uuid, NeutronNetwork delta) {
        if (!networkExists(uuid)) {
            return false;
        }
/* note: because what we get is *not* a delta but (at this point) the updated
 * object, this is much simpler - just replace the value and update the mdsal
 * with it */
        updateMd(delta);
        return true;
    }

    @Override
    public boolean networkInUse(String netUUID) {
        if (!networkExists(netUUID)) {
            return true;
        }
        return false;
    }

    protected NeutronNetwork fromMd(Network network) {
        NeutronNetwork result = new NeutronNetwork();
        result.setAdminStateUp(network.isAdminStateUp());
        result.setNetworkName(network.getName());
        result.setShared(network.isShared());
        result.setStatus(network.getStatus());
// todo remove '-' chars as tenant id doesn't use them
        result.setTenantID(network.getTenantId().getValue());
        result.setID(network.getUuid().getValue());

        NetworkL3Extension l3Extension = network.getAugmentation(NetworkL3Extension.class);
        result.setRouterExternal(l3Extension.isExternal());

        NetworkProviderExtension providerExtension = network.getAugmentation(NetworkProviderExtension.class);
        result.setProviderPhysicalNetwork(providerExtension.getPhysicalNetwork());
        result.setProviderSegmentationID(providerExtension.getSegmentationId());
        result.setProviderNetworkType(NETWORK_MAP.get(providerExtension.getNetworkType()));
        List<NeutronNetwork_Segment> segments = new ArrayList<>();
        if (providerExtension.getSegments() != null) {
            for (Segments segment: providerExtension.getSegments()) {
                NeutronNetwork_Segment neutronSegment = new NeutronNetwork_Segment();
                neutronSegment.setProviderPhysicalNetwork(segment.getPhysicalNetwork());
                neutronSegment.setProviderSegmentationID(segment.getSegmentationId());
                neutronSegment.setProviderNetworkType(NETWORK_MAP.get(segment.getNetworkType()));
                segments.add(neutronSegment);
            }
        }
        result.setSegments(segments);
        return result;
    }

    private void fillExtensions(NetworkBuilder networkBuilder,
                                NeutronNetwork network) {
        NetworkL3ExtensionBuilder l3ExtensionBuilder = new NetworkL3ExtensionBuilder();
        if (network.getRouterExternal() != null) {
            l3ExtensionBuilder.setExternal(network.getRouterExternal());
        }

        NetworkProviderExtensionBuilder providerExtensionBuilder = new NetworkProviderExtensionBuilder();
        if (network.getProviderPhysicalNetwork() != null) {
            providerExtensionBuilder.setPhysicalNetwork(network.getProviderPhysicalNetwork());
        }
        if (network.getProviderSegmentationID() != null) {
            providerExtensionBuilder.setSegmentationId(network.getProviderSegmentationID());
        }
        if (network.getProviderNetworkType() != null) {
            ImmutableBiMap<String, Class<? extends NetworkTypeBase>> mapper =
                NETWORK_MAP.inverse();
            providerExtensionBuilder.setNetworkType(mapper.get(network.getProviderNetworkType()));
        }
        if (network.getSegments() != null) {
            List<Segments> segments = new ArrayList<>();
            long count = 0;
            for( NeutronNetwork_Segment segment : network.getSegments()) {
                count++;
                SegmentsBuilder segmentsBuilder = new SegmentsBuilder();
                if (segment.getProviderPhysicalNetwork() != null) {
                    segmentsBuilder.setPhysicalNetwork(segment.getProviderPhysicalNetwork());
                }
                if (segment.getProviderSegmentationID() != null) {
                    segmentsBuilder.setSegmentationId(segment.getProviderSegmentationID());
                }
                if (segment.getProviderNetworkType() != null) {
                    ImmutableBiMap<String, Class<? extends NetworkTypeBase>> mapper =
                        NETWORK_MAP.inverse();
                    segmentsBuilder.setNetworkType(mapper.get(segment.getProviderNetworkType()));
                }
                segmentsBuilder.setSegmentationIndex(count);
                segments.add(segmentsBuilder.build());
            }
            providerExtensionBuilder.setSegments(segments);
        }
        if (network.getProviderSegmentationID() != null) {
            providerExtensionBuilder.setSegmentationId(network.getProviderSegmentationID());
        }

        networkBuilder.addAugmentation(NetworkL3Extension.class,
                                       l3ExtensionBuilder.build());
        networkBuilder.addAugmentation(NetworkProviderExtension.class,
                                       providerExtensionBuilder.build());
    }

    protected Network toMd(NeutronNetwork network) {
        NetworkBuilder networkBuilder = new NetworkBuilder();
        fillExtensions(networkBuilder, network);

        networkBuilder.setAdminStateUp(network.getAdminStateUp());
        if (network.getNetworkName() != null) {
            networkBuilder.setName(network.getNetworkName());
        }
        if (network.getShared() != null) {
            networkBuilder.setShared(network.getShared());
        }
        if (network.getStatus() != null) {
            networkBuilder.setStatus(network.getStatus());
        }
        if (network.getTenantID() != null) {
            networkBuilder.setTenantId(toUuid(network.getTenantID()));
        }
        if (network.getNetworkUUID() != null) {
            networkBuilder.setUuid(toUuid(network.getNetworkUUID()));
        } else {
            LOGGER.warn("Attempting to write neutron network without UUID");
        }
        return networkBuilder.build();
    }

    protected Network toMd(String uuid) {
        NetworkBuilder networkBuilder = new NetworkBuilder();
        networkBuilder.setUuid(toUuid(uuid));
        return networkBuilder.build();
    }

    @Override
    protected InstanceIdentifier<Network> createInstanceIdentifier(Network network) {
        return InstanceIdentifier.create(Neutron.class)
                .child(Networks.class)
                .child(Network.class,network.getKey());
    }

    protected InstanceIdentifier<Networks> createInstanceIdentifier() {
        return InstanceIdentifier.create(Neutron.class)
                .child(Networks.class);
    }

    public static void registerNewInterface(BundleContext context,
                                            final DataBroker dataBroker,
                                            List<ServiceRegistration<?>> registrations) {
        NeutronNetworkInterface neutronNetworkInterface = new NeutronNetworkInterface(dataBroker);
        ServiceRegistration<INeutronNetworkCRUD> neutronNetworkInterfaceRegistration = context.registerService(INeutronNetworkCRUD.class, neutronNetworkInterface, null);
        if(neutronNetworkInterfaceRegistration != null) {
            registrations.add(neutronNetworkInterfaceRegistration);
        }
    }
}
