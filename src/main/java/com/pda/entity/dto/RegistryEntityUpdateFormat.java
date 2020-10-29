package com.pda.entity.dto;

import com.pda.entity.dao.RegistryEntityWithOsId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryEntityUpdateFormat {
    @JsonProperty(value="Entity")
    private RegistryEntityWithOsId registryEntityWithOsId;

    public RegistryEntityWithOsId getRegistryEntityWithOsId() {
        return registryEntityWithOsId;
    }

    public void setRegistryEntityWithOsId(RegistryEntityWithOsId registryEntityWithOsId) {
        this.registryEntityWithOsId = registryEntityWithOsId;
    }
}
