package com.pda.entity.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Request1 {

    @JsonProperty(value = "EntityDocs")
    private EntityDocs entityDocs;

    public EntityDocs getEntityDocs() {
        return entityDocs;
    }

    public void setEntityDocs(EntityDocs entityDocs) {
        this.entityDocs = entityDocs;
    }
}


