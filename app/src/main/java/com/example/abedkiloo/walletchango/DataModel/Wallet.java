package com.example.abedkiloo.walletchango.DataModel;

import com.google.gson.annotations.SerializedName;

public class Wallet {


    @SerializedName("id")
    private String wallet_id;
    @SerializedName("wallet_name")
    private String wallet_name;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("wallet_amount")
    private String wallet_amount;
    @SerializedName("user")
    private User user;


    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }
    
    public String getWallet_name() {
        return wallet_name;
    }

    public void setWallet_name(String wallet_name) {
        this.wallet_name = wallet_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
