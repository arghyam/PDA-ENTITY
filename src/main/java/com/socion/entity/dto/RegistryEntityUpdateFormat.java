package com.socion.entity.dto;

import com.socion.entity.dao.RegistryEntityWithOsId;
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
