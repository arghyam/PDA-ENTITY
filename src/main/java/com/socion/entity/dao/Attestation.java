package com.socion.entity.dao;

import javax.persistence.Entity;
import javax.persistence.*;


@Table(schema = "attestation")
@Entity(name = "attestation")
public class Attestation {

    public Attestation() {
    }

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "attestation_id")
    private int attestationId;

    private String templateName;

    private String attestationType;

    private String attestationTemplateUrl;

    private String sampleAttestationUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;


    public int getAttestationId() {
        return attestationId;
    }

    public void setAttestationId(int attestationId) {
        this.attestationId = attestationId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAttestationType() {
        return attestationType;
    }

    public void setAttestationType(String attestationType) {
        this.attestationType = attestationType;
    }

    public String getAttestationTemplateUrl() {
        return attestationTemplateUrl;
    }

    public void setAttestationTemplateUrl(String attestationTemplateUrl) {
        this.attestationTemplateUrl = attestationTemplateUrl;
    }

    public String getSampleAttestationUrl() {
        return sampleAttestationUrl;
    }

    public void setSampleAttestationUrl(String sampleAttestationUrl) {
        this.sampleAttestationUrl = sampleAttestationUrl;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
