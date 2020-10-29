package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicDTO implements Serializable {

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String description;

    @NotNull
    private Long programId;

    private Long id;
    private Boolean sessionLinked;

    public Boolean getSessionLinked() {
        return sessionLinked;
    }

    public void setSessionLinked(Boolean sessionLinked) {
        this.sessionLinked = sessionLinked;
    }

    public TopicDTO() {
    }

    public TopicDTO(@NotEmpty @NotNull String name, @NotEmpty @NotNull String description) {
        this.name = name;
        this.description = description;
    }

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

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
