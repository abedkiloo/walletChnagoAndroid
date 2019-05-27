package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.abedkiloo.walletchango.DataModel.Projects;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetails extends AppCompatActivity {


    AppCompatTextView description_appCompatTextView, longer_description_appCompatTextView,
            amount_raised__appCompatTextView, amount_targetted__appCompatTextView, total_investors__appCompatTextView;
    ApiService apiService;
    String project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        //get project_id
        project_id = intent.getStringExtra("project_id");
        //api service
        apiService = AppUtils.getAPIService();
        initView();
        getProjectDetail(project_id);


    }

    private void initView() {
        description_appCompatTextView = findViewById(R.id.short_project_description);
        longer_description_appCompatTextView = findViewById(R.id.longer_description);
        amount_raised__appCompatTextView = findViewById(R.id.raised_amount);
        amount_targetted__appCompatTextView = findViewById(R.id.out_of_amount);
        total_investors__appCompatTextView = findViewById(R.id.total_investors);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    public void getProjectDetail(final String id) {


        apiService.getProject(id).enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                Log.e("ID_", id);

                Projects project = response.body();

                Log.e("Project", response.toString());

                description_appCompatTextView.setText("Created by " + project.getUser().getName());
                longer_description_appCompatTextView.setText(project.getProject_description());
                amount_raised__appCompatTextView.setText(String.valueOf(project.getAmount_deposited()));
                amount_targetted__appCompatTextView.setText(String.valueOf(project.getTarget_investment()));
                total_investors__appCompatTextView.setText(String.valueOf(project.getMembers_subscribed()));
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Log.e("FAILED", t.toString());
            }
        });


    }


}
