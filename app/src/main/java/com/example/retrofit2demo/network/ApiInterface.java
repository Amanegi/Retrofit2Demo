package com.example.retrofit2demo.network;

import com.example.retrofit2demo.model.LoginData;
import com.example.retrofit2demo.model.LoginResponse;
import com.example.retrofit2demo.model.ProjectListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("AccountAPI/GetLoginUser")
    Call<LoginResponse> getLogin(@Body LoginData loginData);

    @GET("ProjectAPI/GetProjectListing")
    Call<ProjectListResponse> getProjects();

}
