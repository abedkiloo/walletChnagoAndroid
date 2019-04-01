package com.example.abedkiloo.walletchango;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectDetails extends AppCompatActivity {


    AppCompatTextView description_appCompatTextView, longer_description_appCompatTextView,
            amount_raised__appCompatTextView, amount_targetted__appCompatTextView, total_investors__appCompatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        initView();
        getProjectDetail("2");


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
                startActivity(new Intent(getApplicationContext(), MainDrawer.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getProjectDetail(final String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        Call<Projects> projectDetailsCall = api.getProject(id);
        projectDetailsCall.enqueue(new Callback<Projects>() {
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
