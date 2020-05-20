package com.socion.entity.dto;

import com.socion.entity.dao.RegistryEntityRolesWithOsId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryEntityRolesUpdatedFormat {
    @JsonProperty(value="EntityRole")
    private RegistryEntityRolesWithOsId registryEntityRolesWithOsId;

    public RegistryEntityRolesWithOsId getRegistryEntityRolesWithOsId() {
        return registryEntityRolesWithOsId;
    }

    public void setRegistryEntityRolesWithOsId(RegistryEntityRolesWithOsId registryEntityRolesWithOsId) {
        this.registryEntityRolesWithOsId = registryEntityRolesWithOsId;
    }
}
