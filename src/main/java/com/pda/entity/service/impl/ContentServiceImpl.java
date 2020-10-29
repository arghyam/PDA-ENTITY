package com.pda.entity.service.impl;

import com.pda.entity.dao.Content;
import com.pda.entity.dao.Topic;
import com.pda.entity.dto.ContentDTO;
import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.exceptions.NotFoundException;
import com.pda.entity.repository.ContentRepository;
import com.pda.entity.service.ContentService;
import com.pda.entity.service.TopicService;
import com.pda.entity.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    TopicService topicService;

    @Autowired
    S3Uploader s3Uploader;

    @Override
    public Content add(ContentDTO contentDTO, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);
        return saveContent(contentDTO);
    }

    private Content saveContent(ContentDTO contentDTO) {
        Topic topic = topicService.get(contentDTO.getTopicId());
        Content content = new Content();
        content.setContentType(contentDTO.getContentType());
        content.setName(contentDTO.getName());
        content.setContentUrl(contentDTO.getUrl());
        content.setActive(true);
        content.setTopic(topic);
        return contentRepository.save(content);
    }

    @Override
    public Content getOne(Long id) {
        Content content = contentRepository.getOne(id);
        if (content == null) {
            throw new NotFoundException("Content not found with id " + id);
        }
        return content;
    }

    @Override
    public Content update(Long id, ContentDTO contentDTO, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);
        Content content = getOne(id);
        content.setContentType(contentDTO.getContentType());
        content.setContentUrl(contentDTO.getContentType());
        content.setName(contentDTO.getName());
        Topic topic = topicService.get(contentDTO.getTopicId());
        if (topic == null) {
            throw new NotFoundException("Content not fount with id " + id);
        }
        content.setTopic(topic);
        return contentRepository.save(content);
    }

    @Override
    public List<Content> getContentByTopic(Long topicId) {
        return contentRepository.getContentByTopicId(topicId);
    }

    @Override
    public void delete(Long id) {
        Content content = getOne(id);
        content.setDeleted(true);
        contentRepository.save(content);
    }

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Override
    public String uploadContent(MultipartFile file, Long topicId, String contentType) {
        ResponseDTO uploadResponse = s3Uploader.uploadContentToS3(file, contentType);
        if (uploadResponse.isSuccess()) {
            String contentUrl = (String) uploadResponse.getResponse();
            saveContent(new ContentDTO(file.getOriginalFilename(), contentType, contentUrl, topicId));
            return contentUrl;
        }
        return uploadResponse.getMessage();
    }

}
