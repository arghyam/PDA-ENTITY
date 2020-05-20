package com.socion.entity.service.impl;

import com.socion.entity.dao.Content;
import com.socion.entity.dao.Program;
import com.socion.entity.dao.Topic;
import com.socion.entity.dto.*;
import com.socion.entity.dto.*;
import com.socion.entity.exceptions.NotFoundException;
import com.socion.entity.repository.TopicRepository;
import com.socion.entity.service.ContentService;
import com.socion.entity.service.ProgramRolesService;
import com.socion.entity.service.ProgramService;
import com.socion.entity.service.TopicService;
import com.socion.entity.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ProgramService programService;

    @Autowired
    ProgramRolesService programRolesService;

    @Autowired
    ContentService contentService;

    @Override
    public Topic convertAndSave(TopicDTO topicDTO, Program program) {
        Topic topic = new Topic();
        topic.setDescription(topicDTO.getDescription());
        topic.setName(topicDTO.getName());
        topic.setActive(true);
        topic.setProgram(program);
        return topicRepository.save(topic);
    }

    @Override
    public Topic create(TopicDTO topicDTO, BindingResult bindingResult) {
        Program program = programService.getOne(topicDTO.getProgramId());
        return convertAndSave(topicDTO, program);
    }


    @Override
    public Topic get(Long id) {
        Topic topic = topicRepository.getOne(id);
        if (topic == null) {
            throw new NotFoundException("Topic not found with id " + id);
        }
        return topic;
    }

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }


    @Override
    public Topic update(TopicDTO topicDTO, BindingResult bindingResult, Long id) {
        Topic topic = get(id);
        topic.setDescription(topicDTO.getDescription());
        topic.setName(topic.getName());
        Program program = programService.getOne(topicDTO.getProgramId());
        if (program == null) {
            throw new NotFoundException("Program not found with id " + id);
        }
        topic.setProgram(program);
        logger.info("sessionlinked bool" + topicDTO.getSessionLinked());
        topic.setSessionLinked(topicDTO.getSessionLinked());
        return topicRepository.save(topic);
    }

    @Override
    public void delete(Long id) {
        Topic topic = get(id);
        topicRepository.delete(topic.getId());
    }

    @Override
    public List<Topic> getTopicByProgramId(Long programId) {
        return topicRepository.getByProgramId(programId);
    }

    @Override
    public TopicSessionLinkedDTO linkSession(TopicSessionLinkedDTO linkedDTO) {
        Topic topic = get(linkedDTO.getTopicId());
        topic.setSessionLinked(linkedDTO.isSessionLinked());
        Topic savedTopic = topicRepository.save(topic);
        return new TopicSessionLinkedDTO(savedTopic.getId(), savedTopic.isSessionLinked());
    }

    @Override
    public ResponseDTO eligibleTrainer(String userId, Long topicId) {
        Topic topic = get(topicId);
        if (topic == null || topic.getProgram() == null) {
            return HttpUtils.onFailure(HttpStatus.NOT_FOUND.value(), "Topic or Program not found");
        }
        return HttpUtils.onSuccess_(programRolesService.checkEligibleTrainer(topic.getProgram().getId(), userId), "Success");
    }

    @Override
    public TopicDetailWithProgramContentDTO getTopicdetails(Long id, boolean deleted) {
        Topic topic = topicRepository.getTopic(id);
        if (topic == null) {
            throw new NotFoundException("Topic not found with id " + id);
        }
        List<Content> content = contentService.getContentByTopic(id);
        return convertTopicDetails(topic, content);
    }

    private TopicDetailWithProgramContentDTO convertTopicDetails(Topic topic, List<Content> content) {
        TopicDTO topicDTO = new TopicDTO(topic.getName(), topic.getDescription());
        topicDTO.setProgramId(topic.getProgram().getId());
        topicDTO.setId(topic.getId());
        Program program = topic.getProgram();
        topicDTO.setProgramId(program.getId());
        ProgramDTO programDTO = new ProgramDTO(program.getName(), program.getDescription(), program.getStartDate().toLocaleString(), program.getEndDate().toLocaleString(), program.getEmail());
        if (null == program.getEntity()) {
            throw new NotFoundException("Entity not found");
        }
        programDTO.setEntityId(program.getEntity().getId());
        programDTO.setEntityName(program.getEntity().getName());
        List<ContentDTO> contentDTOs = new ArrayList<>();
        if (null != content && !content.isEmpty()) {
            content.forEach(c -> {
                contentDTOs.add(new ContentDTO(c.getName(), c.getContentType(), c.getContentUrl(), c.getVimeoUrl(), topic.getId()));
            });
        }

        return new TopicDetailWithProgramContentDTO(topicDTO, programDTO, contentDTOs,topic.isDeleted());
    }

    public ResponseDTO getMultipleTopicDetails(TopicIdsDTO topicIds){
        ResponseDTO responseDTO=new ResponseDTO();
        List<Map<String, Object>> topicInfos = new ArrayList<Map<String, Object>>();
        try{
        for(Long topicId:topicIds.getTopicIds()){
            Map<String, Object> topicInfo = new HashMap<>();
            topicInfo.put(getTopicdetails(topicId,false).getTopic().getId().toString(),getTopicdetails(topicId,false));
            topicInfos.add(topicInfo);
        }}catch (Exception e){
            logger.info("Unable to fetch the topic details.  Exception:{}",e.getMessage());
        }
        responseDTO.setMessage("Sucessfully Fetched topic details");
        responseDTO.setResponse(topicInfos);
        responseDTO.setResponseCode(HttpStatus.OK.value());
        return responseDTO;
    }
}
