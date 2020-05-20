package com.socion.entity.dao;

import com.socion.entity.dto.*;
import com.socion.entity.dto.v2.RegistryRequestClass;
import com.socion.entity.utils.Constant;
import com.socion.entity.dto.*;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.io.IOException;

@Repository
public interface RegistryDao {

    @POST(Constant.REGISRY_ADD_USER)
    Call<RegistryResponse> entities(@Header("x-authenticated-user-token") String adminAccessToken,
                                    @Body RegistryRequest registryRequest) throws IOException;
    @POST(Constant.REGISRY_ADD_USER)
    Call<RegistryResponse> entitiesdocs(@Header("x-authenticated-user-token") String adminAccessToken,
                                    @Body RegistryDocsRequest registryDocsRequest) throws IOException;
    @POST(Constant.REGISRY_ADD_USER)
    Call<RegistryResponse> entitiesroles(@Header("x-authenticated-user-token") String adminAccessToken,
                                        @Body RegistryRolesRequest registryRolesRequest) throws IOException;

    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                              @Body SlimRegistryEntityDto slimRegistryEntityDto) throws IOException;
    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityDocsByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                                @Body SlimRegistryEntityDocsDto slimRegistryEntityDocsDto) throws IOException;
    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityRolesByEntityIdAndUserId(@Header("x-authenticated-user-token") String accessToken,
                                                      @Body SlimRegistryEntityRolesDto slimRegistryEntityRolesDto) throws IOException;

    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityRolesByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                                       @Body RegistryRequestClass registryRequestClass) throws IOException;

    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityRolesTable(@Header("x-authenticated-user-token") String accessToken,
                                                       @Body RegistryRequestClass registryRequestClass) throws IOException;
    @POST(Constant.REGISRY_SEARCH_USER)
    Call<RegistryResponse> searchEntityDocsByOsid(@Header("x-authenticated-user-token") String accessToken,
                                                      @Body SearchEntitywithOsId searchEntitywithOsId) throws IOException;
    @POST(Constant.REGISTRY_UPDATE_USER)
    Call<RegistryResponse> updateEntityByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                                      @Body RegistryRequestWithOsId registryEntityWithOsId) throws IOException;
    @POST(Constant.REGISTRY_UPDATE_USER)
    Call<RegistryResponse> updateEntityDocsByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                                  @Body RegistryDocsRequestWithOsId registryDocsRequestWithOsId) throws IOException;
    @POST(Constant.REGISTRY_UPDATE_USER)
    Call<RegistryResponse> updateEntityRolesByEntityId(@Header("x-authenticated-user-token") String accessToken,
                                                      @Body RegistryRequestRolesWithOsId registryEntityRolesWithOsId) throws IOException;

}
