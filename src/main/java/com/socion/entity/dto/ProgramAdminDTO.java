package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramAdminDTO implements Serializable {

    @NotNull
    @NotEmpty
    private Long entityId;

    @NotNull
    @NotEmpty
    private List<String> programAdmins;

    public ProgramAdminDTO() {
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public List<String> getProgramAdmins() {
        return programAdmins;
    }

    public void setProgramAdmins(List<String> userIds) {
        this.programAdmins = userIds;
    }
}
