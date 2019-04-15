package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;
import com.example.abedkiloo.walletchango.DataModel.User;

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


        apiService.loginUser(user_email, user_password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User user = response.body();
                if (user.getStatus().equals("success")) {
                    // Creating user login session
                    // For testing i am stroing name, email as follow
                    // Use user real data
                    Log.e("USER_ID",user.getId());

                    session.createLoginSession(user.getId(), user.getName(), user.getEmail());

                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    Toast.makeText(Login.this, user.getStatus(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void btnSignUp(View view) {
    }
}
