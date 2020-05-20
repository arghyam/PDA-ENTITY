package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRequestEntityId {
    @JsonProperty(value="Entity")
    private RegisterEntityWithEntityId entity;

    public RegisterEntityWithEntityId getEntity() {
        return entity;
    }

    public void setEntity(RegisterEntityWithEntityId entity) {
        this.entity = entity;
    }
}
