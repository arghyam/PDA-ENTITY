package com.socion.entity.dao;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Set;


@Table(schema = "organization")
@Entity(name = "organization")
public class Organization {

    public Organization() {
    }

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "org_id")
    private int orgId;

    private String orgName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<Attestation> attestations;

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

    public Set<Attestation> getAttestations() {
        return attestations;
    }

    public void setAttestations(Set<Attestation> attestations) {
        this.attestations = attestations;
    }
}
