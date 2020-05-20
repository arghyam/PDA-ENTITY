package com.socion.entity.repository;

import com.socion.entity.dao.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Override
    @Query(value = "select t from topic t WHERE id = ?1 ")
    Topic getOne(Long id);

    @Query(value = "select t from topic t WHERE id = ?1")
    Topic getTopic(Long id);

    @Override
    @Query(value = "select t from topic t WHERE deleted=false")
    List<Topic> findAll();

    @Query(value = "UPDATE topic t set deleted=true where id = ?1")
    void delete(Long id);

    @Query(value = "SELECT t from topic t where program_id = ?1")
    List<Topic> getByProgramId(Long programId);
}
