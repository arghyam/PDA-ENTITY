package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRoles {
    @JsonProperty(value = "EntityRole")
    private EntityRole entityRole;

    public EntityRole getEntityRole() {
        return entityRole;
    }

    public void setEntityRole(EntityRole entityRole) {
        this.entityRole = entityRole;
    }
}
