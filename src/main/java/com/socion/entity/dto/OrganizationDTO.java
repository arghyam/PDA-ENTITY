package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationDTO implements Serializable {

    private int orgId;
    private String orgName;
    private List<AttestationDTO> traineeAttestations;
    private List<AttestationDTO> memberAttestations;

    public OrganizationDTO(int orgId, String orgName) {
        this.orgId = orgId;
        this.orgName = orgName;
    }

    public OrganizationDTO() {
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<AttestationDTO> getTraineeAttestations() {
        return traineeAttestations;
    }

    public void setTraineeAttestations(List<AttestationDTO> traineeAttestations) {
        this.traineeAttestations = traineeAttestations;
    }

    public List<AttestationDTO> getMemberAttestations() {
        return memberAttestations;
    }

    public void setMemberAttestations(List<AttestationDTO> memberAttestations) {
        this.memberAttestations = memberAttestations;
    }
}
