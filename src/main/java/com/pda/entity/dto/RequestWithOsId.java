package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestWithOsId extends RegistryRequest {
    @JsonProperty(value="request")
    private RegistryEntityUpdateFormat entity;

    public RegistryEntityUpdateFormat getEntity() {
        return entity;
    }

    public void setEntity(RegistryEntityUpdateFormat entity) {
        this.entity = entity;
    }
}
