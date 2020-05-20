package com.socion.entity.repository;

import com.socion.entity.dao.EntityRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntityRoleRepository extends JpaRepository<EntityRoles, Integer> {
    @Query(value = "Select distinct * from  entity_role WHERE entity_id=?1 ", nativeQuery = true)
    List<EntityRoles> getByEntityId(Long entityid);
}
