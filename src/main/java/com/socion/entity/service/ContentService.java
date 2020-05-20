package com.socion.entity.service;

import com.socion.entity.dao.Content;
import com.socion.entity.dto.ContentDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService {

    Content add(ContentDTO contentDTO, BindingResult bindingResult);

    Content getOne(Long id);

    Content update(Long id, ContentDTO contentDTO, BindingResult bindingResult);

    List<Content> getContentByTopic(Long topicId);

    void delete(Long id);

    List<Content> getAll();

    String uploadContent(MultipartFile file, Long topicId, String contentType);
}
