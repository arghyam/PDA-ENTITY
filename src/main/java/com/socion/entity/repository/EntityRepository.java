package com.socion.entity.repository;

import com.socion.entity.dao.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {

    @Override
    @Query(value = "select e from entity e WHERE e.id = ?1 and e.deleted=false")
    Entity getOne(Long id);

    @Override
    @Query(value = "select e from entity e WHERE e.deleted=false")
    List<Entity> findAll();
}
