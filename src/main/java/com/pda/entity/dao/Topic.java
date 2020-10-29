package com.pda.entity.dao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@javax.persistence.Entity(name = "topic")
@Table(name = "topic")
public class Topic extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Column(length = 100000)
    private String description;

    public Topic() {
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private boolean sessionLinked;

    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public boolean isSessionLinked() {
        return sessionLinked;
    }

    public void setSessionLinked(boolean sessionLinked) {
        this.sessionLinked = sessionLinked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
