package com.pda.entity.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@javax.persistence.Entity(name = "eligible_program_admin")
@Table(name = "eligible_program_admin")
public class ProgramAdmin extends BaseEntity implements Serializable {

    public ProgramAdmin() {
    }

    @EmbeddedId
    private ProgramAdminPk id;

    @NotNull
    @ManyToOne
    @MapsId(value = "entityId")
    @JoinColumn(name = "entity_id")
    private Entity entity;

    public ProgramAdminPk getId() {
        return id;
    }

    public void setId(ProgramAdminPk id) {
        this.id = id;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


}
