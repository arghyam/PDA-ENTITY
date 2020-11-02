package com.pda.entity.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.pda.entity.dao.Content;
import com.pda.entity.dto.ContentDTO;
import com.pda.entity.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/api/v1/entity")
public class ContentController {

    @Autowired
    ContentService contentService;

    @PostMapping(value = "/content")
    public Content addContent(@Validated @RequestBody ContentDTO contentDTO, BindingResult bindingResult) {
        return contentService.add(contentDTO,bindingResult);
    }

    @GetMapping(value = "/content/{id}")
    public Content get(@PathVariable(value = "id") Long id) {
        return contentService.getOne(id);
    }

    @GetMapping(value = "/contents")
    public List<Content> getAll() {
        return contentService.getAll();
    }

    @GetMapping(value = "/content/topic/{topicId}")
    public List<Content> getContentByTpoicId(@PathVariable(value = "topicId") Long topicId) {
        return contentService.getContentByTopic(topicId);
    }

    @DeleteMapping(value = "/content/{id}")
    public void delete(@PathParam(value = "id") Long id) {
        contentService.delete(id);
    }

    @PutMapping(value = "/content/{id}")
    public Content updateContent(@Validated @RequestBody ContentDTO contentDTO, BindingResult bindingResult,@PathVariable(value = "id") Long id) {
        return contentService.update(id,contentDTO,bindingResult);
    }
    
    @PostMapping(path = "/content/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadContent(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam(name = "contentType", required = false) String contentType, @RequestParam(name = "topicId", required = false) Long topicId) {
		return contentService.uploadContent(file, topicId, contentType);
	}

}
