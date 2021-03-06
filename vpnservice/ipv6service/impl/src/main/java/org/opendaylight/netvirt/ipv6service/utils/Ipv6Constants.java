/*
 * Copyright (c) 2016 Red Hat, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.netvirt.ipv6service.utils;

public class Ipv6Constants {
    public static final int IPv6_ETHTYPE = 34525;
    public static final int ICMP_v6 = 1;

    public static final int ETHTYPE_START = 96;
    public static final int ONE_BYTE  = 8;
    public static final int TWO_BYTES = 16;
    public static final int IPv6_HDR_START = 112;
    public static final int IPv6_NEXT_HDR = 48;
    public static final int ICMPV6_HDR_START = 432;

    public static final int ICMPV6_RA_LENGTH_WO_OPTIONS = 16;
    public static final int ICMPV6_OPTION_SOURCE_LLA_LENGTH = 8;
    public static final int ICMPV6_OPTION_PREFIX_LENGTH = 32;

    public static final int IPV6_DEFAULT_HOP_LIMIT = 64;
    public static final int IPV6_ROUTER_LIFETIME = 4500;
    public static final int IPV6_RA_VALID_LIFETIME = 2592000;
    public static final int IPV6_RA_PREFERRED_LIFETIME = 604800;

    public static final int ICMPv6_TYPE = 58;
    public static final short ICMPv6_RS_CODE = 133;
    public static final short ICMPv6_RA_CODE = 134;
    public static final short ICMPv6_NS_CODE = 135;
    public static final short ICMPv6_NA_CODE = 136;
    public static final short ICMPv6_MAX_HOP_LIMIT = 255;
    public static final int ICMPV6_OFFSET = 54;

    public static final String DHCPV6_OFF = "DHCPV6_OFF";
    public static final String IPV6_SLAAC = "IPV6_SLAAC";
    public static final String IPV6_DHCPV6_STATEFUL = "DHCPV6_STATEFUL";
    public static final String IPV6_DHCPV6_STATELESS = "DHCPV6_STATELESS";
    public static final String IPV6_AUTO_ADDRESS_SUBNETS = IPV6_SLAAC + IPV6_DHCPV6_STATELESS;

    public static final String IP_VERSION_V4 = "IPv4";
    public static final String IP_VERSION_V6 = "IPv6";
}
