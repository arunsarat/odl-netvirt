/*
 * Copyright (c) 2016 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.netvirt.elan.internal;

//import org.opendaylight.controller.md.sal.binding.api.DataBroker;
//import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
//import org.opendaylight.controller.md.sal.common.api.data.AsyncDataBroker;
//import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
//import org.opendaylight.vpnservice.mdsalutil.AbstractDataChangeListener;
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.itm.op.rev150701.TunnelsState;
//import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.itm.op.rev150701.tunnels_state.StateTunnelList;
//import org.opendaylight.yangtools.concepts.ListenerRegistration;
//import org.opendaylight.yangtools.yang.binding.DataObject;
//import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigInteger;

public class ElanItmEventListener{

}
//public class ElanItmEventListener extends AbstractDataChangeListener<DataObject> implements AutoCloseable {
//FIXME: This class is to be made functional once ITM is added
//    private static final Logger logger = LoggerFactory.getLogger(ElanItmEventListener.class);
//    private final DataBroker broker;
//    private ListenerRegistration<DataChangeListener> listenerRegistration;
//    private ElanInterfaceManager elanInterfaceManager;
//
//    public ElanItmEventListener(final DataBroker db, final ElanInterfaceManager ifManager) {
//        super(StateTunnelList.class);
//        broker = db;
//        elanInterfaceManager = ifManager;
//        registerListener(db);
//    }
//
//    private void registerListener(final DataBroker db) {
//        try {
//            listenerRegistration = broker.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL,
//                    getWildCardPath(), ElanItmEventListener.this, AsyncDataBroker.DataChangeScope.SUBTREE);
//        } catch (final Exception e) {
//            logger.error("ITM Monitor Interfaces DataChange listener registration fail!", e);
//            throw new IllegalStateException("ITM Monitor registration Listener failed.", e);
//        }
//    }
//
//    private InstanceIdentifier<StateTunnelList> getWildCardPath() {
//        return InstanceIdentifier.create(TunnelsState.class).child(StateTunnelList.class);
//    }
//
//    @Override
//    public void close() throws Exception {
//        if (listenerRegistration != null) {
//            try {
//                listenerRegistration.close();
//            } catch (final Exception e) {
//                logger.error("Error when cleaning up DataChangeListener.", e);
//            }
//            listenerRegistration = null;
//        }
//    }
//
//    @Override
//    protected void remove(InstanceIdentifier<StateTunnelList> identifier, StateTunnelList del) {
//
//    }
//
//    @Override
//    protected void update(InstanceIdentifier<StateTunnelList> identifier, StateTunnelList original, StateTunnelList update) {
//        BigInteger srcDpId = update.getSourceDPN();
//        BigInteger dstDpId = update.getDestinationDPN();
//        logger.trace("ITM Tunnel state event changed from :{} to :{} for transportZone:{}",original.isLogicalTunnelState(), update.isLogicalTunnelState(), update.getLogicalTunnelGroupName());
//
//        if(update.isLogicalTunnelState()) {
//            logger.trace("ITM Tunnel State is Up b/w srcDpn: {} and dstDpn: {}", srcDpId, dstDpId);
//            elanInterfaceManager.handleTunnelStateEvent(srcDpId, dstDpId);
//        }
//    }
//
//    @Override
//    protected void add(InstanceIdentifier<StateTunnelList> identifier, StateTunnelList add) {
//        BigInteger srcDpId =  add.getSourceDPN();
//        BigInteger dstDpId = add.getDestinationDPN();
//        logger.trace("ITM Tunnel state event:{} for transportZone:{} of {}", add.isLogicalTunnelState(), add.getLogicalTunnelGroupName());
//
//        if(add.isLogicalTunnelState()) {
//            logger.trace("ITM Tunnel State is Up b/w srcDpn: {} and dstDpn: {}", srcDpId, dstDpId);
//            elanInterfaceManager.handleTunnelStateEvent(srcDpId, dstDpId);
//        }
//    }
//}
