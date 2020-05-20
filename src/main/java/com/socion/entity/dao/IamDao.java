package com.socion.entity.dao;

import com.socion.entity.dto.UserRequestTDO;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.http.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface IamDao {

    @GET("private/details/{userId}")
    @Headers("Content-Type:application/json")
    Call<RegistryUser> getUser(@Path("userId") String userIds) throws IOException;

    @POST("private/all")
    @Headers("Content-Type:application/json")
    Call<List<LinkedHashMap>> getUsers(@Body UserRequestTDO userRequestTDO) throws IOException;

}
