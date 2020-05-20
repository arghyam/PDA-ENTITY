package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class RegistryRolesRequest {
    protected String id;
    protected String ver;
    protected Long ets;
    protected RequestParams params;

    @JsonIgnore
    protected String requestMapString;
    @JsonIgnore
    protected JsonNode requestMapNode;
    @JsonProperty(value="request")
    private RequestRoles request;

    public RequestRoles getRequest() {
        return request;
    }

    public void setRequest(RequestRoles request) {
        this.request = request;
    }

    public RegistryRolesRequest(RequestParams params, RequestRoles request, String id) {
        this.ver = "1.0";
        this.ets = System.currentTimeMillis();
        this.params = params;
        this.id=id;
        this.request=request;


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

    public Long getEts() {
        return ets;
    }

    public void setEts(Long ets) {
        this.ets = ets;
    }

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }



    public String getRequestMapString() {
        return requestMapString;
    }

    public void setRequestMapString(String requestMapString) {
        this.requestMapString = requestMapString;
    }

    public JsonNode getRequestMapNode() {
        return requestMapNode;
    }

    public void setRequestMapNode(JsonNode requestMapNode) {
        this.requestMapNode = requestMapNode;
    }
}
