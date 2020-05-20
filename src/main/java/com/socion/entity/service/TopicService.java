package com.socion.entity.service;

import com.socion.entity.dao.Program;
import com.socion.entity.dao.Topic;
import com.socion.entity.dto.*;
import com.socion.entity.dto.*;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface TopicService {

    public Topic convertAndSave(TopicDTO topicDTO, Program program);

    public Topic create(TopicDTO topicDTO, BindingResult bindingResult);

    Topic get(Long id);



    List<Topic> getAll();

    Topic update(TopicDTO topicDTO, BindingResult bindingResult, Long id);

    void delete(Long id);

    List<Topic> getTopicByProgramId(Long programId);

    TopicSessionLinkedDTO linkSession(TopicSessionLinkedDTO linkedDTO);

    ResponseDTO eligibleTrainer(String userId, Long topicId);

    public TopicDetailWithProgramContentDTO getTopicdetails(Long id, boolean deleted);

    public ResponseDTO getMultipleTopicDetails(TopicIdsDTO topicIds);
}
