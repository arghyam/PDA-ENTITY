package com.pda.entity.dto;

import com.pda.entity.dao.RegistryEntityRolesWithOsId;
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
