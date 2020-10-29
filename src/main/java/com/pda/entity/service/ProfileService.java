package com.pda.entity.service;

import com.pda.entity.dto.ProfileTemplateDto;
import com.pda.entity.dto.ResponseDTO;

public interface ProfileService {

    public ResponseDTO generateProfileCard(ProfileTemplateDto templateDto, String picId) throws Exception;

}
