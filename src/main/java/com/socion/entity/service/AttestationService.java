package com.socion.entity.service;

import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.dto.TemplateDto;
import org.dom4j.DocumentException;

import java.io.IOException;

public interface AttestationService {
    public ResponseDTO userAttestation(TemplateDto templateDto) throws IOException, DocumentException;
}
