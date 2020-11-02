package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchEntitywithOsId {
    @JsonProperty(value="request")
    private SlimRequestEntityOsId request;
    private String id;
    private String ver;


    public SlimRequestEntityOsId getRequest() {
        return request;
    }

    public void setRequest(SlimRequestEntityOsId request) {
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
