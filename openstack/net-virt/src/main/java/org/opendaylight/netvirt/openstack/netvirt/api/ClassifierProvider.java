/*
 * Copyright (c) 2014, 2015 Red Hat, Inc. and others. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.netvirt.openstack.netvirt.api;


/**
 *  This interface allows Classifier flows to be written to devices
 */
public interface ClassifierProvider {
    void programLocalInPort(Long dpidLong, String segmentationId, Long inPort, String attachedMac, boolean write);
    void programLocalInPortSetVlan(Long dpidLong, String segmentationId, Long inPort, String attachedMac, boolean write);
    void programDropSrcIface(Long dpidLong, Long inPort, boolean write);
    void programTunnelIn(Long dpidLong, String segmentationId, Long ofPort, boolean write);
    void programVlanIn(Long dpidLong, String segmentationId, Long ethPort, boolean write);
    void programLLDPPuntRule(Long dpidLong);
    void programGotoTable(Long dpidLong, boolean write);
}
