package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicSessionLinkedDTO implements Serializable {

    @NotEmpty
    @NotNull
    private Long topicId;

    @NotEmpty
    @NotNull
    private boolean isSessionLinked;

    public TopicSessionLinkedDTO() {
    }

    public TopicSessionLinkedDTO(@NotEmpty @NotNull Long topicId, @NotEmpty @NotNull boolean isSessionLinked) {
        this.topicId = topicId;
        this.isSessionLinked = isSessionLinked;
    }


    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public boolean isSessionLinked() {
        return isSessionLinked;
    }

    public void setSessionLinked(boolean isSessionLinked) {
        this.isSessionLinked = isSessionLinked;
    }

}
