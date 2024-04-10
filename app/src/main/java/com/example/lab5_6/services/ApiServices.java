package com.example.lab5_6.services;


import com.example.lab5_6.Model.Response_Model;
import com.example.lab5_6.Model.Students;
import com.example.lab5_6.Model.User;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {


    public static String BASE_URL = "http://10.0.2.2:3000/api/";

    //Annotations @GET cho method GET và url phương gọi

    @GET("get-list-student")
    Call<Response_Model<ArrayList<Students>>> getListStudents();
    //Call giá trị trả về của api

    @POST("add-student")
    Call<Response_Model<Students>> addStudnets(@Body Students students);

    //Param url sẽ bỏ vào {}
    @DELETE("delete-student-by-id/{id}")
    Call<Response_Model<Students>> deleteStudentsById(@Path("id") String id);

    @PUT("update-student-by-id/{id}")
    Call<Response_Model<Students>> updateStudentsById(@Path("id") String id, @Body Students students);

    @Multipart
    @POST("register-send-email")
    Call<Response_Model<User>> register(@Part("username") RequestBody username,
                                  @Part("password") RequestBody password,
                                  @Part("email") RequestBody email,
                                  @Part("name") RequestBody name,
                                  @Part MultipartBody.Part avatar);
    @POST("login")
    Call<Response_Model<User>> login(@Body User user);

}
