package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterEntityDocsWithOsId {
    @JsonProperty(value="entity_document_id")
    private String entity_document_id;

    public String getEntity_document_id() {
        return entity_document_id;
    }

    public void setEntity_document_id(String entity_document_id) {
        this.entity_document_id = entity_document_id;
    }
}
