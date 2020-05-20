package com.socion.entity.dto;

import com.socion.entity.dao.RegistryEntityDocsWithOsId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryEntityDocsUpdateFormat {
    @JsonProperty(value="EntityDocs")
    private RegistryEntityDocsWithOsId registryEntityWithOsId;

    public RegistryEntityDocsWithOsId getRegistryEntityWithOsId() {
        return registryEntityWithOsId;
    }

    public void setRegistryEntityWithOsId(RegistryEntityDocsWithOsId registryEntityWithOsId) {
        this.registryEntityWithOsId = registryEntityWithOsId;
    }
}



