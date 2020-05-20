package com.socion.entity.repository;

import com.socion.entity.dao.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    List<Organization> findByOrgIdIn(List<Integer> orgIds);
}

