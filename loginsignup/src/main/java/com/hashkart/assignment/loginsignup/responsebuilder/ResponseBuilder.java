package com.hashkart.assignment.loginsignup.responsebuilder;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBuilder<T> {
    private ResponseStatus status;
    private T data;
    private String message;
    private LocalDateTime timestamp;

    private ResponseBuilder(ResponseStatus responseStatus, T data) {
        this.status = responseStatus;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    private ResponseBuilder(ResponseStatus responseStatus, T data, String message) {
        this.status = responseStatus;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    private ResponseBuilder(ResponseStatus responseStatus, String message) {
        this.status = responseStatus;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ResponseBuilder<T> success(T t) {
        return new ResponseBuilder<>(ResponseStatus.SUCCESS, t);
    }

    public static <T> ResponseBuilder<T> success(T t, String message) {
        return new ResponseBuilder<>(ResponseStatus.SUCCESS, t, message);
    }

    public static <T> ResponseBuilder<T> success(String message) {
        return new ResponseBuilder<>(ResponseStatus.SUCCESS, message);
    }

    public static <T> ResponseBuilder<T> error(T t) {
        return new ResponseBuilder<>(ResponseStatus.FAILED, t);
    }

    public static <T> ResponseBuilder<T> error(T t, String message) {
        return new ResponseBuilder<>(ResponseStatus.FAILED, t, message);
    }

    public static <T> ResponseBuilder<T> error(String message) {
        return new ResponseBuilder<>(ResponseStatus.FAILED, message);
    }

}