package com.socion.entity.repository;

import com.socion.entity.dao.ProgramAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramAdminRepository extends JpaRepository<ProgramAdmin, Long> {

    @Override
    List<ProgramAdmin> findAll();

    @Query(value = "Select e from eligible_program_admin  e WHERE e.id.userId=?1")
    List<ProgramAdmin> getByUserId(String userId);

    @Query("Delete from eligible_program_admin e where e.id.userId=?2 and e.id.entityId=?1")
    void deleteByEntityIdAndUserId(long entityId, String userId);

    @Query("Delete from eligible_program_admin e where e.id.entityId=?1")
    void deleteByEntityId(long entityId);

    @Query(value = "Select e from eligible_program_admin  e WHERE e.id.entityId=?1")
    List<ProgramAdmin> getByEntityId(Long entityId);

}
