package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryDocsRequestWithOsId extends RegistryRequest {
    @JsonProperty(value = "request")
    private RegistryEntityDocsUpdateFormat registryEntityDocsUpdateFormat;



    public RegistryDocsRequestWithOsId(RequestParams params, RegistryEntityDocsUpdateFormat registryEntityDocsUpdateFormat, String id) {

        this.ver = "1.0";
        this.ets = System.currentTimeMillis();
        this.params = params;
        this.registryEntityDocsUpdateFormat = registryEntityDocsUpdateFormat;

        this.id = id;
    }

    public RegistryEntityDocsUpdateFormat getRegistryEntityDocsUpdateFormat() {
        return registryEntityDocsUpdateFormat;
    }

    public void setRegistryEntityDocsUpdateFormat(RegistryEntityDocsUpdateFormat registryEntityDocsUpdateFormat) {
        this.registryEntityDocsUpdateFormat = registryEntityDocsUpdateFormat;
    }
}
