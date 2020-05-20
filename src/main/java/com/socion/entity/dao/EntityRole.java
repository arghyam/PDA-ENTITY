package com.socion.entity.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@javax.persistence.Entity(name = "entity_role")
@Table(name = "entity_role")
public class EntityRole extends BaseEntity {

    public EntityRole() {
    }

    @EmbeddedId
    private EntityRolePk id;
    @NotNull
    @MapsId("entityId")
    @ManyToOne
    @JoinColumn(name = "entity_id")
    private Entity entity;

    public EntityRolePk getId() {
        return id;
    }

    public void setId(EntityRolePk id) {
        this.id = id;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


}
