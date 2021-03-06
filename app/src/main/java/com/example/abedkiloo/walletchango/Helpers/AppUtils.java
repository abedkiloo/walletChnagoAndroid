package com.example.abedkiloo.walletchango.Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public AppUtils() {
    }

    public static final String URL = "http://192.168.43.101/walletChango";
    public static final String BASE_URL = URL+"/public/api/";
    public static final String Image_BASE_URL =URL+"/storage/app/public/upload/images/projects/original/";


    public static ApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
    public boolean isEmailValid(String email)
    {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}