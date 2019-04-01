package com.example.abedkiloo.walletchango;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "http://192.168.43.101/walletChango/public/api/";


    @GET("project")
    Call<List<Projects>> getProjects();

    @GET("project/{id}")
    Call<Projects> getProject(@Path("id") String id);

}
