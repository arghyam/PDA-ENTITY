package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRequestEntityOsId {
    @JsonProperty(value="EntityDocs")
    private RegisterEntityDocsWithOsId entityDocs;

    public RegisterEntityDocsWithOsId getEntityDocs() {
        return entityDocs;
    }

    public void setEntityDocs(RegisterEntityDocsWithOsId entityDocs) {
        this.entityDocs = entityDocs;
    }
}
