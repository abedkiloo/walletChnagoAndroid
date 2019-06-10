package com.example.abedkiloo.walletchango.Helpers;

import com.example.abedkiloo.walletchango.DataModel.Projects;
import com.example.abedkiloo.walletchango.DataModel.User;
import com.example.abedkiloo.walletchango.DataModel.Wallet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {



    @GET("project")
    Call<List<Projects>> getProjects();

    @GET("project/{id}")
    Call<Projects> getProject(@Path("id") String id);

    @POST("user/login")
    @FormUrlEncoded
    Call<User> loginUser(@Field("email") String email, @Field("password") String password);

    @GET("wallet/{id}")
    Call<Wallet> getWalletDetail(@Path("id") String id);


    @POST("user/deposit")
    @FormUrlEncoded
    Call<Wallet> depositMpesa(@Field("wallet_id") String wallet_id, @Field("user_id") String user_id,
                              @Field("amount") String amount, @Field("transaction_type") String transaction_type
    );

}
