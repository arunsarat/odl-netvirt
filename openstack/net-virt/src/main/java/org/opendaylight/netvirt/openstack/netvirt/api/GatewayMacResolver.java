/*
 * Copyright (c) 2015 Brocade Communications Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.netvirt.openstack.netvirt.api;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;

import com.google.common.util.concurrent.ListenableFuture;

/**
*
* @author Anil Vishnoi (avishnoi@Brocade.com)
*
*/

public interface GatewayMacResolver {

    /**
     * Method will trigger the mac resolution for gateway IP. If user set periodicRefresh to true,
     * it will periodically trigger the gateway resolution after a specific time interval using the
     * given source IP and MAC addresses. It will also cache the source IP and MAC in case of periodicRefresh.
     * If periodicRefresh is false, it will just do one time gateway resolution and won't cache any internal data.
     * If user call the same method with different source ip and mac address, GatewayMacResolver service will
     * update the internally cached data with these new source ip and mac address and will use it as per
     * periodicRefresh flag.
     * @param gatewayMacResolverListener An optional listener for mac update callback (can be null)
     * @param externalNetworkBridgeDpid This bridge will be used for sending ARP request
     * @param refreshExternalNetworkBridgeDpidIfNeeded Instructs resolver to change bridge dpid if resolve is failing
     * @param gatewayIp ARP request will be send for this ip address
     * @param sourceIpAddress Source IP address for the ARP request (localhost)
     * @param sourceMacAddress Source MAC address for the ARP request (localhost)
     * @param periodicRefresh Do you want to periodically refresh the gateway mac?
     * @return ListenableFuture that contains the mac address of gateway ip.
     */
    ListenableFuture<MacAddress> resolveMacAddress(
            final GatewayMacResolverListener gatewayMacResolverListener,
            final Long externalNetworkBridgeDpid, final Boolean refreshExternalNetworkBridgeDpidIfNeeded,
            final Ipv4Address gatewayIp, final Ipv4Address sourceIpAddress,
            final MacAddress sourceMacAddress, final Boolean periodicRefresh);

    /**
     * Method will stop the periodic refresh of the given gateway ip address.
     * @param gatewayIp Gateway IP Address
     */
    void stopPeriodicRefresh(final Ipv4Address gatewayIp);
}
