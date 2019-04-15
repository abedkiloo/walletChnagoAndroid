package com.example.abedkiloo.walletchango.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abedkiloo.walletchango.DataModel.Wallet;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WalletDetail extends Fragment {
    // Session Manager Class
    SessionManager session;
    // api service for request to db

    ApiService apiService;


    AppCompatTextView amount;

    HashMap<String, String> user_details;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        apiService = AppUtils.getAPIService();
        session = new SessionManager(getContext());


        View rootView = inflater.inflate(R.layout.wallet_detail, container, false);

        amount = rootView.findViewById(R.id.wallet_amount);


        user_details = session.getUserDetails();
        wallet_detail();
        return rootView;

        // Session Manager
    }

    public void wallet_detail() {
Log.e("WALLER",user_details.get(SessionManager.KEY_USER_ID));
        apiService.getWalletDetail(user_details.get(SessionManager.KEY_USER_ID)).enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {

                Wallet wallet = response.body();
                Log.e("AMOUNT_", String.valueOf(response.body()) );
                amount.setText(wallet.getWallet_amount());
            }

            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {

            }
        });

    }
}