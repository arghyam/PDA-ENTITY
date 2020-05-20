package com.socion.entity.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@javax.persistence.Entity(name = "program_roles")
@Table(name = "program_roles")
public class ProgramRoles extends BaseEntity {

    public ProgramRoles() {
    }

    @EmbeddedId
    private ProgramRolePk id;

    @NotNull
    @MapsId("programId")
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    public ProgramRolePk getId() {
        return id;
    }

    public void setId(ProgramRolePk id) {
        this.id = id;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

}
