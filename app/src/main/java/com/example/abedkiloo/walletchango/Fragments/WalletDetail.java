package com.example.abedkiloo.walletchango.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abedkiloo.walletchango.Activities.DepositAmount;
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


    AppCompatTextView amount, deposit_amount;

    HashMap<String, String> user_details;


    //wallet detail
    String wallet_id;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        apiService = AppUtils.getAPIService();
        session = new SessionManager(getContext());


        View rootView = inflater.inflate(R.layout.wallet_detail, container, false);

        amount = rootView.findViewById(R.id.wallet_amount);
        deposit_amount = rootView.findViewById(R.id.deposit_amount);
        deposit_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deposit_money = new Intent(getContext(), DepositAmount.class);
                deposit_money.putExtra("wallet_id", wallet_id);

                startActivity(deposit_money);
            }
        });

        //hash map for use session
        user_details = session.getUserDetails();

        //get wallet detail from end point
        wallet_detail();
        return rootView;

        // Session Manager
    }

    public void wallet_detail() {
        Log.e("WALLET","EXE");
//        user_details.get(SessionManager.KEY_USER_ID)
        apiService.getWalletDetail("2").enqueue(new Callback<Wallet>() {
            @Override
            public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                Log.e("WALLET", response.toString());
                Wallet wallet = response.body();
                amount.setText(getString(R.string.your_wallet_amount_is) + wallet.getWallet_amount());
                wallet_id = wallet.getWallet_id();
            }


            @Override
            public void onFailure(Call<Wallet> call, Throwable t) {

            }
        });

    }
}