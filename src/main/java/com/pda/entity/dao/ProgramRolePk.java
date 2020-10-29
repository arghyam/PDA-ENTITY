package com.pda.entity.dao;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class ProgramRolePk implements Serializable {


    private String userId;

    private long programId;

    @NotNull
    @NotEmpty
    private String role;

    public ProgramRolePk() {
    }

    public ProgramRolePk(String userId, long programId, String role) {
        this.userId = userId;
        this.programId = programId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ProgramRolePk that = (ProgramRolePk) o;

        return userId.equals(that.userId) && programId == that.programId && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}