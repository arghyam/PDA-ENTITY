package com.socion.entity.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistryRequestClass {
    @JsonProperty(value="request")
    private SlimRequestEntityRoleObjects request;
    private String id="open-saber.registry.search";
    private String ver="1.0";

    public SlimRequestEntityRoleObjects getRequest() {
        return request;
    }

    public void setRequest(SlimRequestEntityRoleObjects request) {
        this.request = request;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
