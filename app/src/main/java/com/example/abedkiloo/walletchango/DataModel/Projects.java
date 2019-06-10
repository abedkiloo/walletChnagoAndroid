package com.example.abedkiloo.walletchango.DataModel;

import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.google.gson.annotations.SerializedName;

public class Projects {
    private String id;


    @SerializedName("image_url")
    private String image_url;
    @SerializedName("project_name")
    private String project_name;

    @SerializedName("project_description")
    private String project_description;


    @SerializedName("project_details")
    private String project_details;

    @SerializedName("group")
    private Group group;

    @SerializedName("minimum_deposit")
    private String minimum_deposit;
    @SerializedName("project_type")
    private String project_type;
    @SerializedName("project_target_amount")
    private int target_investment;
    @SerializedName("members_subscribed")
    private int members_subscribed;
    @SerializedName("amount_collected")
    private int amount_deposited;
    @SerializedName("start_date")
    private String start_date;
    @SerializedName("end_date")
    private String end_date;
    @SerializedName("user")
    private User user;

    public Projects() {
    }


    public Projects(String id, String project_name, String project_description, int amount_deposited, int target_investment) {
        this.id = id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.amount_deposited = amount_deposited;
        this.target_investment = target_investment;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getMinimum_deposit() {
        return minimum_deposit;
    }

    public void setMinimum_deposit(String minimum_deposit) {
        this.minimum_deposit = minimum_deposit;
    }

    public int getTarget_investment() {
        return target_investment;
    }

    public void setTarget_investment(int target_investment) {
        this.target_investment = target_investment;
    }

    public int getAmount_deposited() {
        return amount_deposited;
    }

    public void setAmount_deposited(int amount_deposited) {
        this.amount_deposited = amount_deposited;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getMembers_subscribed() {
        return members_subscribed;
    }

    public void setMembers_subscribed(int members_subscribed) {
        this.members_subscribed = members_subscribed;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getProject_details() {
        return project_details;
    }

    public void setProject_details(String project_details) {
        this.project_details = project_details;
    }

    public String getImage_url() {
        return  image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

