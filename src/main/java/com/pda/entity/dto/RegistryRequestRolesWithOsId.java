package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryRequestRolesWithOsId extends  RegistryRequest{
    @JsonProperty(value = "request")
    private RegistryEntityRolesUpdatedFormat registryEntityRolesUpdatedFormat;



    public RegistryRequestRolesWithOsId(RequestParams params, RegistryEntityRolesUpdatedFormat registryEntityRolesUpdatedFormat, String id) {

        this.ver = "1.0";
        this.ets = System.currentTimeMillis();
        this.params = params;
        this.registryEntityRolesUpdatedFormat = registryEntityRolesUpdatedFormat;

        this.id = id;
    }

    public RegistryEntityRolesUpdatedFormat getRegistryEntityRolesUpdatedFormat() {
        return registryEntityRolesUpdatedFormat;
    }

    public void setRegistryEntityRolesUpdatedFormat(RegistryEntityRolesUpdatedFormat registryEntityRolesUpdatedFormat) {
        this.registryEntityRolesUpdatedFormat = registryEntityRolesUpdatedFormat;
    }
}
