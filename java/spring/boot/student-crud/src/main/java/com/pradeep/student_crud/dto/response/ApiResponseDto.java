package com.pradeep.student_crud.dto.response;

import org.springframework.http.HttpStatus;

public class ApiResponseDto<T> {
    private boolean success;
    private int statusCode;
    private T data;
    private String message;

    private ApiResponseDto(){}
    private ApiResponseDto(boolean success, int statusCode, String message){
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
    }
    private ApiResponseDto(boolean success, T data, int statusCode, String message){
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDto<T> success(){
        ApiResponseDto<T> apiResponseDto  = new ApiResponseDto<>(true, HttpStatus.OK.value(), "Success");
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> success(T data, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(true, data, HttpStatus.OK.value(), message);
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> success(HttpStatus statusCode, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(true, null, statusCode.value(), message);
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> success(HttpStatus statusCode, T data, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(true, data, statusCode.value(), message);
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> error(){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> error(T data, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(false, data, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> error(HttpStatus statusCode, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(false, null, statusCode.value(), message);
        return apiResponseDto;
    }

    public static <T> ApiResponseDto<T> error(HttpStatus statusCode, T data, String message){
        ApiResponseDto<T> apiResponseDto =
                new ApiResponseDto<>(false, data, statusCode.value(), message);
        return apiResponseDto;
    }

    public ApiResponseDto<T> statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponseDto<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResponseDto<T> message(String message) {
        this.message = message;
        return this;
    }
}
