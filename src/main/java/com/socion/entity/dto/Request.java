package com.socion.entity.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    @JsonProperty(value="Entity")
    private EntityRegistryDto entity;


    public EntityRegistryDto getEntity() {
        return entity;
    }

    public void setEntity(EntityRegistryDto entity) {
        this.entity = entity;
    }
}
