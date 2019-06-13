package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abedkiloo.walletchango.DataModel.ResponseModel;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    AppCompatEditText email, password;

    // api service for request to db
    ApiService apiService;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = AppUtils.getAPIService();

        // Session Manager
        session = new SessionManager(getApplicationContext());
        initView();
    }

    private void initView() {
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
    }

    public void btnLogin(View view) {
        final String user_email = email.getText().toString();
        String user_password = password.getText().toString();
        AppUtils appUtils = new AppUtils();
        if (user_email.equals("")) {
            email.setError("Please enter an email address");

        }
        if (user_password.equals("")) {
            password.setError("Please enter a password");

        }
        if (!appUtils.isEmailValid(user_email)) {
            email.setError("Please enter a valid email address");
            return;
        }

        apiService.loginUser(user_email, user_password).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("LOG_RES", String.valueOf(response.body()));

                ResponseModel user = response.body();
                Log.e("USER", String.valueOf(user.getData()));
                if (user.getStatus_code().equals("0")) {

                    session.createLoginSession(user.getData().getId(), user.getData().getName(),
                            user.getData().getEmail() ,user.getData().getToken());

                    startActivity(new Intent(Login.this, MainDrawer.class));
                } else {
//                    Toast.makeText(Login.this, user.getStatus(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("EROR", t.toString());
            }
        });
    }

    public void btnSignUp(View view) {
    }
}
