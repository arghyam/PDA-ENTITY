package com.socion.entity.service;

import com.socion.entity.dto.TemplateDto;
import com.socion.entity.service.impl.AttestationServiceImpl;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(FileUtils.class)
public class AttestationServiceImplTest {

    @InjectMocks
    AttestationServiceImpl attestationService;

    private static final String DATE = "2019-02-02 10:10:10";
    private static final String TRAINEE = "TRAINEE";
    private static final String MEMBER = "MEMBER";

    private TemplateDto templateDto = new TemplateDto();

    @Before
    public void SetUp() {
        templateDto.setSessionEndDate(DATE);
        templateDto.setUserRole(TRAINEE);
    }

  /*  @Test
    public void userAttestationTest() throws IOException, DocumentException {

        //attestationService.userAttestation(templateDto);
    }*/

}
