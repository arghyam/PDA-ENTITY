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
                    appContext.getAdminUserpassword(), appContext.getClientId(), appContext.getGrantType(), null).execute().body();
        } catch (IOException e) {
            LOGGER.error("error logged as", e);
        }

        return adminAccessTokenResponse.getAccessToken();

    }
}

// Adding the keycloak client secret as a parameter for authentication.
