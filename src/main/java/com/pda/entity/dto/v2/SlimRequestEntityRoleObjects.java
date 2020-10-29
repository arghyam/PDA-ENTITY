package com.pda.entity.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlimRequestEntityRoleObjects {
    @JsonProperty(value="EntityRole")
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
