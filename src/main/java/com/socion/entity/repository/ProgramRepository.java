package com.socion.entity.repository;

import com.socion.entity.dao.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(value = "SELECT p from program p where entity_id = ?1")
    public List<Program> getProgramByEntityId(Long entityId);


    @Override
    @Query(value = "SELECT p from program p where id=?1")
    Program getOne(Long id);
}
