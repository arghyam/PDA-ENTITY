package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttestationDTO implements Serializable {
    public AttestationDTO() {
    }

    private int attestationId;
    private String templateName;
    private String attestationType;
    private String attestationTemplateUrl;
    private String sampleAttestationUrl;

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
}
