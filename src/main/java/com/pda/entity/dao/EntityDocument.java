package com.pda.entity.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity(name = "entity_documents")
@Table(name = "entity_documents")
public class EntityDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contentType;
    private String contentUrl;
    private String vimeoUrl;
    private String name;
    private String attachment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "entity_id")
    private com.pda.entity.dao.Entity entity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getVimeoUrl() {
        return vimeoUrl;
    }

    public void setVimeoUrl(String vimeoUrl) {
        this.vimeoUrl = vimeoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public com.pda.entity.dao.Entity getEntity() {
        return entity;
    }

    public void setEntity(com.pda.entity.dao.Entity entity) {
        this.entity = entity;
    }
}
