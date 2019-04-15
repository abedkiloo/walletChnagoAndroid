package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.abedkiloo.walletchango.DataModel.Wallet;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepositAmount extends AppCompatActivity {
    SessionManager sessionManager;
    ApiService apiService;


    //edit texts
    AppCompatEditText ed_amount_deposit;

    //Geting sav user
    HashMap<String, String> user_details;

    //wallet id
    String wallet_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_amount);
        sessionManager = new SessionManager(getApplicationContext());
        apiService = AppUtils.getAPIService();


        //view utils
        ed_amount_deposit = findViewById(R.id.ed_amount_deposit);

        //user details
        user_details = sessionManager.getUserDetails();

//        get wallet wallet i from intent
        Bundle bundle = getIntent().getExtras();
        wallet_id = bundle.getString("wallet_id");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_ninjas:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    public void btnPayMpesa(View view) {

        String deposit_amount = ed_amount_deposit.getText().toString();
        if (deposit_amount.equals("")) {
            ed_amount_deposit.setError("Please enter amount to deposit");
            return;
        } else {

            apiService.depoitMpesa(wallet_id, user_details.get(SessionManager.KEY_USER_ID), deposit_amount, "1").enqueue(new Callback<Wallet>() {
                @Override
                public void onResponse(Call<Wallet> call, Response<Wallet> response) {


                    Log.e("WALLET_RESPONSE", String.valueOf(response.body()));
                }

                @Override
                public void onFailure(Call<Wallet> call, Throwable t) {
                    Log.e("ERROR_DEPOSIT", String.valueOf(t));
                }
            });
        }
    }
}
