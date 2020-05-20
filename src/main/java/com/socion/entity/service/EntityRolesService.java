package com.socion.entity.service;


import com.socion.entity.dto.MemberDTO;

import java.util.List;


public interface EntityRolesService {

    List<MemberDTO> getEntityadmindetails(Long id);

}
