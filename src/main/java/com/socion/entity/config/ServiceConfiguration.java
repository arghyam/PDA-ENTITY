package com.socion.entity.config;

import com.socion.entity.dao.IamDao;
import com.socion.entity.dao.KeycloakDao;
import com.socion.entity.dao.RegistryDao;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class ServiceConfiguration {

    @Autowired
    AppContext appContext;

    private OkHttpClient setClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS);

        return httpClient.build();

    }


    @Bean
    public IamDao initIam() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setClient())
                .baseUrl(appContext.getIamBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IamDao.class);
    }
    @Bean
    public RegistryDao initRegistry() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setClient())
                .baseUrl(appContext.getRegistryBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(RegistryDao.class);
    }
    @Bean
    public KeycloakDao initKeyCloak() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setClient())
                .baseUrl(appContext.getKeyCloakServiceUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(KeycloakDao.class);

    }
}
