package com.example.abedkiloo.walletchango.Helpers;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

//
//    public static HashMap<String, String> getUser() {
//        return MainDrawer.sessionManager.getUserDetails();
//    }


    public static Retrofit getClient(String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

// add your other interceptors …
// add logging as last interceptor
        httpClient.addInterceptor(logging);

//        if (getUser().get(SessionManager.KEY_TOKEN) != null) {
//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMjcuMC4wLjE6ODAwMFwvYXBpXC9hdXRoXC9hdXRoZW50aWNhdGUiLCJpYXQiOjE1NjA0MDIwOTEsImV4cCI6MTU2MDQwNTY5MSwibmJmIjoxNTYwNDAyMDkxLCJqdGkiOiJtSjRsVmw0Unl2WGpLUmtDIiwic3ViIjo0LCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.ZWoEbyurpATGdnc4_sr8bb5L6c-dtA34yrurE5aJ3LA" )
//                            .build();
//                    return chain.proceed(newRequest);
//                }
//            }).build();if (retrofit == null) {
//                retrofit = new Retrofit.Builder()
//                    .client(client)
//                        .baseUrl(baseUrl)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//            }
//        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())

                    .build();
        }
        return retrofit;
    }
}
