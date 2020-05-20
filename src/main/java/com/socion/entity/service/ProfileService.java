package com.socion.entity.service;

import com.socion.entity.dto.ProfileTemplateDto;
import com.socion.entity.dto.ResponseDTO;

public interface ProfileService {

    public ResponseDTO generateProfileCard(ProfileTemplateDto templateDto,String picId) throws Exception;

}
