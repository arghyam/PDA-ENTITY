package com.pda.entity.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlimRegistryEntityDto {

    private SlimRequestEntityId request;
    private String id;
    private String ver;

    public SlimRequestEntityId getRequest() {
        return request;
    }

    public void setRequest(SlimRequestEntityId request) {
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
