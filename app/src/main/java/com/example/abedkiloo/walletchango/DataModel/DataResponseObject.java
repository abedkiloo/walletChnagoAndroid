package com.example.abedkiloo.walletchango.DataModel;

import com.google.gson.annotations.SerializedName;

public class DataResponseObject {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("phone_no")
    String password;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @SerializedName("token")
    String token;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

}
