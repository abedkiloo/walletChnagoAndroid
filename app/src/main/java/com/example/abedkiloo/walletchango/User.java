package com.example.abedkiloo.walletchango;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
