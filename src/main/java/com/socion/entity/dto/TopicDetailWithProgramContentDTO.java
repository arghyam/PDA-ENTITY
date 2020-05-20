package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicDetailWithProgramContentDTO implements Serializable {

    @NotNull
    private TopicDTO topic;

    @NotNull
    private ProgramDTO program;

    @NotNull
    private List<ContentDTO> content;

    private boolean deleted;


    public TopicDetailWithProgramContentDTO() {
    }

    public TopicDetailWithProgramContentDTO(@NotNull TopicDTO topic, @NotNull ProgramDTO program, @NotNull List<ContentDTO> content, boolean deleted) {
        this.topic = topic;
        this.program = program;
        this.content = content;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public ProgramDTO getProgram() {
        return program;
    }

    public void setProgram(ProgramDTO program) {
        this.program = program;
    }

    public List<ContentDTO> getContent() {
        return content;
    }

    public void setContent(List<ContentDTO> content) {
        this.content = content;
    }



}
