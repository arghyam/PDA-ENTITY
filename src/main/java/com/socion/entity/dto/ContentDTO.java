package com.socion.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDTO implements Serializable {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String contentType;
    @NotEmpty
    @NotNull
    private String url;

    private String vimeo_url;

    public String getVimeo_url() {
        return vimeo_url;
    }

    public void setVimeo_url(String vimeo_url) {
        this.vimeo_url = vimeo_url;
    }


    @NotNull
    @NotEmpty
    private Long topicId;

    public ContentDTO(@NotEmpty @NotNull String name, @NotEmpty @NotNull String contentType,
                      @NotEmpty @NotNull String url, @NotNull @NotEmpty Long topicId) {
        this.name = name;
        this.contentType = contentType;
        this.url = url;
        this.topicId = topicId;
    }

    public ContentDTO(@NotEmpty @NotNull String name, @NotEmpty @NotNull String contentType, @NotEmpty @NotNull String url, String vimeo_url, @NotNull @NotEmpty Long topicId) {
        this.name = name;
        this.contentType = contentType;
        this.url = url;
        this.vimeo_url = vimeo_url;
        this.topicId = topicId;
    }

    public ContentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
