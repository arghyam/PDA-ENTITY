package com.socion.entity.repository;

import com.socion.entity.dao.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(value = "SELECT c FROM content c WHERE topic_id = ?1 and deleted=false")
    List<Content> getContentByTopicId(Long topiId);

    @Override
    @Query(value = "SELECT c FROM content c WHERE deleted=false")
    List<Content> findAll();

    @Override
    @Query(value = "SELECT c FROM content c WHERE id = ?1 and deleted=false")
    Content getOne(Long aLong);
}
