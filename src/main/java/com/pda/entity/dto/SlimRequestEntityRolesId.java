package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRequestEntityRolesId {
    @JsonProperty(value="EntityRole")
    private RegisterEntityRolesWithEntityId entityRoles;

    public RegisterEntityRolesWithEntityId getEntityRoles() {
        return entityRoles;
    }

    public void setEntityRoles(RegisterEntityRolesWithEntityId entityRoles) {
        this.entityRoles = entityRoles;
    }
}
