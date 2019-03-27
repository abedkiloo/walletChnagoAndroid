package com.example.abedkiloo.walletchango;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.43.101/walletChango/public/api/";


    @GET("project")
    Call<List<Projects>> getProjects();

}
