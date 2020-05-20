package com.socion.entity.repository;

import com.socion.entity.dao.Attestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttestationRepository extends JpaRepository<Attestation, Integer> {

    @Query(value = "Select * from attestation where org_id= ?1", nativeQuery = true)
    List<Attestation> findAttestationsByOrgId(int orgId);

}

