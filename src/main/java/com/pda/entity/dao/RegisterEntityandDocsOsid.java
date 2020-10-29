package com.pda.entity.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegisterEntityandDocsOsid {
    @JsonProperty(value="EntityDocs")
    private List<RegistryEntityDocsWithOsId> registryEntityDocsWithOsId;
    @JsonProperty(value="Entity")
    private RegistryEntityWithOsId registryEntityWithOsId;





    public RegistryEntityWithOsId getRegistryEntityWithOsId() {
        return registryEntityWithOsId;
    }

    public void setRegistryEntityWithOsId(RegistryEntityWithOsId registryEntityWithOsId) {
        this.registryEntityWithOsId = registryEntityWithOsId;
    }

    public List<RegistryEntityDocsWithOsId> getRegistryEntityDocsWithOsId() {
        return registryEntityDocsWithOsId;
    }

    public void setRegistryEntityDocsWithOsId(List<RegistryEntityDocsWithOsId> registryEntityDocsWithOsId) {
        this.registryEntityDocsWithOsId = registryEntityDocsWithOsId;
    }
}
