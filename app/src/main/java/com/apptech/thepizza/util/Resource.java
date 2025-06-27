package com.apptech.thepizza.util;

public class Resource<T> {
    private T data;
    private Status status;
    private String message;

    public Resource(Status status,T data,String message){
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> Resource<T> loading(){
        return new Resource<>(Status.LOADING,null,null);
    }

    public static <T> Resource<T> success(T data){
        return new Resource<>(Status.SUCCESS,data,null);
    }

    public static <T> Resource<T> failure(String message){
        return new Resource<>(Status.LOADING,null,message);
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
