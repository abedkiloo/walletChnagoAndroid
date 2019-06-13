package com.example.abedkiloo.walletchango.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abedkiloo.walletchango.Activities.CreateFundRequest;
import com.example.abedkiloo.walletchango.Adapters.ProjectAdapter;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.SessionManager;
import com.example.abedkiloo.walletchango.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Projects extends Fragment {
    //a list to store all the products
    List<com.example.abedkiloo.walletchango.DataModel.Projects> projectsList = new ArrayList<>();


    // Session Manager Class
    SessionManager session;
    // api service for request to db

    ApiService apiService;

    //the recyclerview
    RecyclerView recyclerView;

    ProjectAdapter adapter;

    com.example.abedkiloo.walletchango.DataModel.Projects projects;


    AppCompatTextView amount, deposit_amount;

    HashMap<String, String> user_details;


    //wallet detail
    String wallet_id;

    HashMap<String, String> user;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        apiService = AppUtils.getAPIService();
        session = new SessionManager(getContext());
        user = session.getUserDetails();


        View rootView = inflater.inflate(R.layout.projects, container, false);


        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateFundRequest.class));
            }
        });
        //initializing the projectlist
        projectsList = new ArrayList<>();

//getting the recyclerview from xml
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ProjectAdapter(getContext(), projectsList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        Log.e("USER_TOKEN", user.get(SessionManager.KEY_TOKEN));

        getProjects();

        return rootView;

        // Session Manager
    }

    private void getProjects() {


        //creating the api interface
        apiService.getProjects().enqueue(new Callback<List<com.example.abedkiloo.walletchango.DataModel.Projects>>() {
            @Override
            public void onResponse(Call<List<com.example.abedkiloo.walletchango.DataModel.Projects>> call, Response<List<com.example.abedkiloo.walletchango.DataModel.Projects>> response) {
                Log.e("ALL_PROJECTS", String.valueOf(response.body()));
                //In this point we got our Projects list

                List<com.example.abedkiloo.walletchango.DataModel.Projects> projectsList2 = response.body();

                com.example.abedkiloo.walletchango.DataModel.Projects projects2;
//
                for (int i = 0; i < projectsList2.size(); i++) {
                    projects2 = new com.example.abedkiloo.walletchango.DataModel.Projects();
                    projects2.setId(projectsList2.get(i).getId());
                    projects2.setProject_name(projectsList2.get(i).getProject_name());
                    projects2.setProject_description(projectsList2.get(i).getProject_description());
                    projects2.setAmount_deposited(projectsList2.get(i).getAmount_deposited());
                    projects2.setTarget_investment(projectsList2.get(i).getTarget_investment());
                    projects2.setImage_url(projectsList2.get(i).getImage_url());
                    projectsList.add(projects2);

                }

                adapter.notifyDataSetChanged();

                //creating recyclerview adapter


                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<List<com.example.abedkiloo.walletchango.DataModel.Projects>> call, Throwable t) {
                Log.e("RETROFIT_ERROR", t.toString());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}