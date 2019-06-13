package com.example.abedkiloo.walletchango.DataModel;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("success")
    String success;
    @SerializedName("errors")
    String errors;
    @SerializedName("status_code")
    String status_code;
    @SerializedName("status_message")
    String status_message;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    DataResponseObject data;

    public DataResponseObject getData() {
        return data;
    }

    public void setData(DataResponseObject data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
