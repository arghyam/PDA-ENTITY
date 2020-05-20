package com.socion.entity.dao;

import com.socion.entity.dto.AccessTokenResponseDTO;
import com.socion.entity.utils.Constant;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface KeycloakDao {


    @POST(Constant.GENERATE_ACCESS_TOKEN)
    @FormUrlEncoded
    Call<AccessTokenResponseDTO> generateAccessTokenUsingCredentials(@Path("realm") String realm, @Field("username") String username,
                                                                     @Field("password") String password,
                                                                     @Field("client_id") String clientId,
                                                                     @Field("grant_type") String grantType,
                                                                     @Field("client_secret") String clientSecret);

}
