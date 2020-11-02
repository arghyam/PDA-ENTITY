package com.pda.entity.service;

import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.dto.TemplateDto;
import org.dom4j.DocumentException;

import java.io.IOException;

public interface AttestationService {
    public ResponseDTO userAttestation(TemplateDto templateDto) throws IOException, DocumentException;
}
