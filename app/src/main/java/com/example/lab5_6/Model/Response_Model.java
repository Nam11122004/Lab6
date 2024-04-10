package com.example.lab5_6.Model;

public class Response_Model<T> {
    private int status;
    private String messenger;
    //T là kiểu Generic
    private T data;

    public Response_Model() {
    }
    private String token;
    private String refreshToken;

    public Response_Model(int status, String messenger, T data,String token, String refreshToken) {
        this.status = status;
        this.messenger = messenger;
        this.data = data;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}