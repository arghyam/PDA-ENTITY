package com.pda.entity.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRequestEntityDocsId {
    @JsonProperty(value="EntityDocs")
    private RegisterEntityDocsWithEntityId entityDocs;

    public RegisterEntityDocsWithEntityId getEntityDocs() {
        return entityDocs;
    }

    public void setEntityDocs(RegisterEntityDocsWithEntityId entityDocs) {
        this.entityDocs = entityDocs;
    }
}
