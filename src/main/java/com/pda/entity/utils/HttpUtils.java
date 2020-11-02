package com.pda.entity.utils;

import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.exceptions.ValidationError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

public class HttpUtils {


    public static ResponseDTO onSuccess(Object responseObject, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(HttpStatus.OK.value());
        responseDTO.setMessage(message);
        if (responseObject != null) {
            Gson gson = new GsonBuilder().create();
            String response = (gson).toJson(responseObject);
            responseDTO.setResponse(response);
        }
        return responseDTO;
    }

    public static ResponseDTO onSuccess_(Object responseObject, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(HttpStatus.OK.value());
        responseDTO.setMessage(message);
        responseDTO.setResponse(responseObject);
        return responseDTO;
    }

    public static ResponseDTO onFailure(int statusCode, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(statusCode);
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public static ResponseDTO onFailure(int statusCode, String message, List<ValidationError> validationError) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponseCode(statusCode);
        responseDTO.setMessage(message);
        responseDTO.setResponse(validationError);
        return responseDTO;
    }

    public static String convertJsonObjectToString(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    public static <T extends Object> T convertStringToJsonObject(String jsonString, Class<T> type) {
        Gson gson = new GsonBuilder().create();
        return (gson).fromJson(jsonString, type);
    }


}
