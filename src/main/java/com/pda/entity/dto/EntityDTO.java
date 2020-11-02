package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityDTO implements Serializable {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String description;

    public EntityDTO() {
    }

    private List<String> programAdmins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getProgramAdmin() {
        return programAdmins;
    }

    public void setProgramAdmins(List<String> programAdmins) {
        this.programAdmins = programAdmins;
    }
}
