package com.example.abedkiloo.walletchango.DataModel;

import com.google.gson.annotations.SerializedName;

public class Transactions {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Group getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(Group wallet_id) {
        this.wallet_id = wallet_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    private String id;


    @SerializedName("amount")
    private String amount;

    @SerializedName("wallet_id")
    private Group wallet_id;

    @SerializedName("transaction_type")
    private String transaction_type;


    @SerializedName("user")
    private User user;



    public Transactions() {
    }







    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

