package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRegistryEntityRolesDto {
    @JsonProperty(value="request")
    private SlimRequestEntityRolesId request;
    private String id;
    private String ver;

    public SlimRequestEntityRolesId getRequest() {
        return request;
    }

    public void setRequest(SlimRequestEntityRolesId request) {
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
