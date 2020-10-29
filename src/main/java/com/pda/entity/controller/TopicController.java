package com.pda.entity.controller;

import java.util.List;

import com.pda.entity.dao.Topic;
import com.pda.entity.dto.*;
import com.pda.entity.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pda.entity.service.TopicService;

@RestController
@RequestMapping(value = "/api/v1/entity")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping(value = "/topic")
    public Topic add(@Validated @RequestBody TopicDTO topicDTO, BindingResult bindingResult) {
        return topicService.create(topicDTO,bindingResult);
    }

    @GetMapping(value = "/topic/detail/{id}/status/{deleted}")
    public TopicDetailWithProgramContentDTO getTopic(@PathVariable(value = "id")  Long id, @PathVariable(value = "deleted") boolean deleted) {
        return topicService.getTopicdetails(id,deleted);
    }

    @PostMapping(value = "/topic/details")
    public ResponseDTO getMultipleTopic(@RequestBody TopicIdsDTO topicIds) {
        return topicService.getMultipleTopicDetails(topicIds);
    }
    
    @GetMapping(value = "/topic/{id}")
    public Topic get(@PathVariable(value = "id")  Long id) {
        return topicRepository.getTopic(id);
    }
    
    @PutMapping(value = "/topic/linkSession")
    public TopicSessionLinkedDTO linkSessionToTopic(@RequestBody TopicSessionLinkedDTO dto) {
        return topicService.linkSession(dto);
    }

    @GetMapping(value = "/topic")
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @PutMapping(value = "/topic/{id}")
    public Topic update(@Validated @RequestBody TopicDTO topicDTO, BindingResult bindingResult,@PathVariable(value = "id") Long id) {
        return topicService.update(topicDTO,bindingResult,id);
    }

    @DeleteMapping(value = "/topic/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        topicService.delete(id);
    }

    @DeleteMapping(value = "/topic/program/{programId}")
    public List<Topic> getByProgramId(@PathVariable(value = "programId") Long programId) {
        return topicService.getTopicByProgramId(programId);
    }

    @GetMapping(value = "/eligible/topic/{topicId}/user/{userId}")
    public ResponseDTO eligibelTrainer(@PathVariable(value = "userId") String userId,@PathVariable(value = "topicId") Long topicId) {
        return topicService.eligibleTrainer(userId,topicId);
    }
}
