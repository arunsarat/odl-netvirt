/*
 * Copyright (C) 2015 Red Hat, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.netvirt.openstack.netvirt.translator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * OpenStack Neutron v2.0 Firewall as a service
 * (FWaaS) bindings. See OpenStack Network API
 * v2.0 Reference for description of  the fields.
 * The implemented fields are as follows:
 *
 * tenant_id               uuid-str
 * name                    String
 * description             String
 * admin_state_up          Bool
 * status                  String
 * shared                  Bool
 * firewall_policy_id      uuid-str
 * protocol                String
 * ip_version              Integer
 * source_ip_address       String (IP addr or CIDR)
 * destination_ip_address  String (IP addr or CIDR)
 * source_port             Integer
 * destination_port        Integer
 * position                Integer
 * action                  String
 * enabled                 Bool
 * id                      uuid-str
 * http://docs.openstack.org/api/openstack-network/2.0/openstack-network.pdf
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)

public class NeutronFirewallRule implements Serializable, INeutronObject {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    String firewallRuleUUID;

    @XmlElement(name = "tenant_id")
    String firewallRuleTenantID;

    @XmlElement(name = "name")
    String firewallRuleName;

    @XmlElement(name = "description")
    String firewallRuleDescription;

    @XmlElement(name = "status")
    String firewallRuleStatus;

    @XmlElement(defaultValue = "false", name = "shared")
    Boolean firewallRuleIsShared;

    @XmlElement(name = "firewall_policy_id")
    String firewallRulePolicyID;

    @XmlElement(name = "protocol")
    String firewallRuleProtocol;

    @XmlElement(name = "ip_version")
    Integer firewallRuleIpVer;

    @XmlElement(name = "source_ip_address")
    String firewallRuleSrcIpAddr;

    @XmlElement(name = "destination_ip_address")
    String firewallRuleDstIpAddr;

    @XmlElement(name = "source_port")
    Integer firewallRuleSrcPort;

    @XmlElement(name = "destination_port")
    Integer firewallRuleDstPort;

    @XmlElement(name = "position")
    Integer firewallRulePosition;

    @XmlElement(name = "action")
    String firewallRuleAction;

    @XmlElement(name = "enabled")
    Boolean firewallRuleIsEnabled;

    public Boolean getFirewallRuleIsEnabled() {
        return firewallRuleIsEnabled;
    }

    public void setFirewallRuleIsEnabled(Boolean firewallRuleIsEnabled) {
        this.firewallRuleIsEnabled = firewallRuleIsEnabled;
    }

    public String getFirewallRuleAction() {
        return firewallRuleAction;
    }

    public void setFirewallRuleAction(String firewallRuleAction) {
        this.firewallRuleAction = firewallRuleAction;
    }

    public Integer getFirewallRulePosition() {
        return firewallRulePosition;
    }

    public void setFirewallRulePosition(Integer firewallRulePosition) {
        this.firewallRulePosition = firewallRulePosition;
    }

    public Integer getFirewallRuleDstPort() {
        return firewallRuleDstPort;
    }

    public void setFirewallRuleDstPort(Integer firewallRuleDstPort) {
        this.firewallRuleDstPort = firewallRuleDstPort;
    }

    public Integer getFirewallRuleSrcPort() {
        return firewallRuleSrcPort;
    }

    public void setFirewallRuleSrcPort(Integer firewallRuleSrcPort) {
        this.firewallRuleSrcPort = firewallRuleSrcPort;
    }

    public String getFirewallRuleDstIpAddr() {
        return firewallRuleDstIpAddr;
    }

    public void setFirewallRuleDstIpAddr(String firewallRuleDstIpAddr) {
        this.firewallRuleDstIpAddr = firewallRuleDstIpAddr;
    }

    public String getFirewallRuleSrcIpAddr() {
        return firewallRuleSrcIpAddr;
    }

    public void setFirewallRuleSrcIpAddr(String firewallRuleSrcIpAddr) {
        this.firewallRuleSrcIpAddr = firewallRuleSrcIpAddr;
    }

    public Integer getFirewallRuleIpVer() {
        return firewallRuleIpVer;
    }

    public void setFirewallRuleIpVer(Integer firewallRuleIpVer) {
        this.firewallRuleIpVer = firewallRuleIpVer;
    }

    public String getFirewallRuleProtocol() {
        return firewallRuleProtocol;
    }

    public void setFirewallRuleProtocol(String firewallRuleProtocol) {
        this.firewallRuleProtocol = firewallRuleProtocol;
    }

    public String getFirewallRulePolicyID() {
        return firewallRulePolicyID;
    }

    public void setFirewallRulesPolicyID(String firewallRulePolicyID) {
        this.firewallRulePolicyID = firewallRulePolicyID;
    }

    public Boolean getFirewallRuleIsShared() {
        return firewallRuleIsShared;
    }

    public void setFirewallRuleIsShared(Boolean firewallRuleIsShared) {
        this.firewallRuleIsShared = firewallRuleIsShared;
    }

    public String getFirewallRuleStatus() {
        return firewallRuleStatus;
    }

    public void setFirewallRuleStatus(String firewallRuleStatus) {
        this.firewallRuleStatus = firewallRuleStatus;
    }

    public String getFirewallRuleDescription() {
        return firewallRuleDescription;
    }

    public void setFirewallRuleDescription(String firewallRuleDescription) {
        this.firewallRuleDescription = firewallRuleDescription;
    }

    public String getFirewallRuleName() {
        return firewallRuleName;
    }

    public void setFirewallRuleName(String firewallRuleName) {
        this.firewallRuleName = firewallRuleName;
    }

    public String getFirewallRuleTenantID() {
        return firewallRuleTenantID;
    }

    public void setFirewallRuleTenantID(String firewallRuleTenantID) {
        this.firewallRuleTenantID = firewallRuleTenantID;
    }

    public String getID() {
        return firewallRuleUUID;
    }

    public void setID(String id) {
        firewallRuleUUID = id;
    }

    // @deprecated use getID()
    public String getFirewallRuleUUID() {
        return firewallRuleUUID;
    }

    // @deprecated use setID()
    public void setFireWallRuleID(String firewallRuleUUID) {
        this.firewallRuleUUID = firewallRuleUUID;
    }

    public NeutronFirewallRule extractFields(List<String> fields) {
        NeutronFirewallRule ans = new NeutronFirewallRule();
        for (String s : fields) {
            switch (s) {
                case "id":
                    ans.setID(this.getID());
                    break;
                case "tenant_id":
                    ans.setFirewallRuleTenantID(this.getFirewallRuleTenantID());
                    break;
                case "name":
                    ans.setFirewallRuleName(this.getFirewallRuleName());
                    break;
                case "description":
                    ans.setFirewallRuleDescription(this.getFirewallRuleDescription());
                    break;
                case "status":
                    ans.setFirewallRuleStatus(this.getFirewallRuleStatus());
                    break;
                case "shared":
                    ans.setFirewallRuleIsShared(firewallRuleIsShared);
                    break;
                case "firewall_policy_id":
                    ans.setFirewallRulesPolicyID(this.getFirewallRulePolicyID());
                    break;
                case "protocol":
                    ans.setFirewallRuleProtocol(this.getFirewallRuleProtocol());
                    break;
                case "source_ip_address":
                    ans.setFirewallRuleSrcIpAddr(this.getFirewallRuleSrcIpAddr());
                    break;
                case "destination_ip_address":
                    ans.setFirewallRuleDstIpAddr(this.getFirewallRuleDstIpAddr());
                    break;
                case "source_port":
                    ans.setFirewallRuleSrcPort(this.getFirewallRuleSrcPort());
                    break;
                case "destination_port":
                    ans.setFirewallRuleDstPort(this.getFirewallRuleDstPort());
                    break;
                case "position":
                    ans.setFirewallRulePosition(this.getFirewallRulePosition());
                    break;
                case "action":
                    ans.setFirewallRuleAction(this.getFirewallRuleAction());
                    break;
                case "enabled":
                    ans.setFirewallRuleIsEnabled(firewallRuleIsEnabled);
                    break;
            }

        }
        return ans;
    }

    @Override
    public String toString() {
        return "firewallPolicyRules{" +
            "firewallRuleUUID='" + firewallRuleUUID + '\'' +
            ", firewallRuleTenantID='" + firewallRuleTenantID + '\'' +
            ", firewallRuleName='" + firewallRuleName + '\'' +
            ", firewallRuleDescription='" + firewallRuleDescription + '\'' +
            ", firewallRuleStatus='" + firewallRuleStatus + '\'' +
            ", firewallRuleIsShared=" + firewallRuleIsShared +
            ", firewallRulePolicyID=" + firewallRulePolicyID +
            ", firewallRuleProtocol='" + firewallRuleProtocol + '\'' +
            ", firewallRuleIpVer=" + firewallRuleIpVer +
            ", firewallRuleSrcIpAddr='" + firewallRuleSrcIpAddr + '\'' +
            ", firewallRuleDstIpAddr='" + firewallRuleDstIpAddr + '\'' +
            ", firewallRuleSrcPort=" + firewallRuleSrcPort +
            ", firewallRuleDstPort=" + firewallRuleDstPort +
            ", firewallRulePosition=" + firewallRulePosition +
            ", firewallRuleAction='" + firewallRuleAction + '\'' +
            ", firewallRuleIsEnabled=" + firewallRuleIsEnabled +
            '}';
    }

    public void initDefaults() {
    }
}
