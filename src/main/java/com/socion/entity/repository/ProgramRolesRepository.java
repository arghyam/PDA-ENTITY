package com.socion.entity.repository;

import com.socion.entity.dao.ProgramRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramRolesRepository extends CrudRepository<ProgramRoles, Long> {

    @Query(value = "SELECT s from program_roles s where s.id.programId=?1 and s.id.userId=?2 and s.id.role='TRAINER' and s.deleted=false")
    List<ProgramRoles> checkEligibeTrainer(long id, String userId);

    @Query(value = "Select e from program_roles  e WHERE e.id.programId=?1")
    List<ProgramRoles> getByProgramId(Long programId);

    @Query(value = "select count(*)>0 from program_roles where program_id=?1 and role=?2 and user_id in (?3)", nativeQuery = true)
    Boolean isRoleAlreadyAddedtoProgram(Long id, String role, List<String> userId);
}
