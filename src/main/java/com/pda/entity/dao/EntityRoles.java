package com.pda.entity.dao;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "entity_role")
@Table(name = "entity_role")
public class EntityRoles {
    public EntityRoles() {
    }

    @EmbeddedId
    private EntityRolePk entityRolePk;

    @ManyToOne
    @JoinColumn(name = "id")
    private com.pda.entity.dao.Entity entity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "updated_at")
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;

    public com.pda.entity.dao.Entity getEntity() {
        return entity;
    }

    public void setEntity(com.pda.entity.dao.Entity entity) {
        this.entity = entity;
    }

    public EntityRolePk getEntityRolePk() {
        return entityRolePk;
    }

    public void setEntityRolePk(EntityRolePk entityRolePk) {
        this.entityRolePk = entityRolePk;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
