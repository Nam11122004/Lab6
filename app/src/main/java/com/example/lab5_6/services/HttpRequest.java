package com.example.lab5_6.services;


import static com.example.lab5_6.services.ApiServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    //Biến Interface ApiServices
    private ApiServices apiServices;

    public HttpRequest(){
        //create retrofit
        apiServices = new Retrofit.Builder()
                .baseUrl(BASE_URL)//khởi tạo đối tượng retrofit và cấu hình thông số
                .addConverterFactory(GsonConverterFactory.create())//chuyển đổi đối tượng gson sang java
                .build().create(ApiServices.class);
    }

    public ApiServices callApi(){
        //Get retrofit
        return apiServices;
    }
}

