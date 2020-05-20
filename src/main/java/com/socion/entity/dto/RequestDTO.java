package com.socion.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO implements Serializable {

    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String description;
    @NotEmpty
    @NotNull
    private List<String> programAdmins;
    @Valid
    private List<ProgramDTO> programs;

    public String getName() {
        return name;
    }

    public RequestDTO() {
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

    public List<String> getProgramAdmins() {
        return programAdmins;
    }

    public void setProgramAdmins(List<String> programAdmins) {
        this.programAdmins = programAdmins;
    }

    public List<ProgramDTO> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramDTO> programs) {
        this.programs = programs;
    }
}
