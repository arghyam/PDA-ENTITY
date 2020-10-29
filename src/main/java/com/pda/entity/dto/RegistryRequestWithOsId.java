package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryRequestWithOsId extends RegistryRequest {
    @JsonProperty(value = "request")
    private RegistryEntityUpdateFormat registryEntityUpdateFormat;



    public RegistryRequestWithOsId(RequestParams params, RegistryEntityUpdateFormat registryEntityUpdateFormat, String id) {

        this.ver = "1.0";
        this.ets = System.currentTimeMillis();
        this.params = params;
        this.registryEntityUpdateFormat = registryEntityUpdateFormat;

        this.id = id;
    }

    public RegistryEntityUpdateFormat getRegistryEntityUpdateFormat() {
        return registryEntityUpdateFormat;
    }

    public void setRegistryEntityUpdateFormat(RegistryEntityUpdateFormat registryEntityUpdateFormat) {
        this.registryEntityUpdateFormat = registryEntityUpdateFormat;
    }
}
