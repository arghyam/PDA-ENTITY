package com.socion.entity.service.impl;



import com.socion.entity.config.AppContext;
import com.socion.entity.dao.KeycloakDao;
import com.socion.entity.dto.AccessTokenResponseDTO;
import com.socion.entity.service.KeycloakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import com.socion.entity.utils.Constant;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import java.util.List;

@Component
@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    private KeycloakDao keycloakDao;

    @Autowired
    AppContext appContext;


    private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakServiceImpl.class);


    @Override
    public String generateAccessToken(String username) {
        AccessTokenResponseDTO adminAccessTokenResponse = null;
        LOGGER.debug("Generating access Token for user : {} ", username);
        try {
            adminAccessTokenResponse = keycloakDao.generateAccessTokenUsingCredentials(appContext.getRealm(), appContext.getAdminUserName(),
                    appContext.getAdminUserpassword(), appContext.getClientId(), appContext.getGrantType(), appContext.getClientSecret()).execute().body();
        } catch (IOException e) {
            LOGGER.error("error logged as", e);
        }

        return adminAccessTokenResponse.getAccessToken();

    }
}

