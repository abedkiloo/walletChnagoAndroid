package com.example.abedkiloo.walletchango.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abedkiloo.walletchango.DataModel.Projects;
import com.example.abedkiloo.walletchango.Helpers.ApiService;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.CircleDisplay;
import com.example.abedkiloo.walletchango.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDetails extends AppCompatActivity {


    AppCompatTextView description_appCompatTextView, longer_description_appCompatTextView, total_investors__textViewDesc,
            amount_raised__appCompatTextView, amount_targetted__appCompatTextView, total_investors__appCompatTextView;
    ApiService apiService;
    String project_id;
    AppCompatSeekBar seekBar;
    AppCompatImageView project_image;
    CircleDisplay circleDisplay;


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
        total_investors__textViewDesc = findViewById(R.id.textViewDesc);
        seekBar = findViewById(R.id.seekBar);
        project_image = findViewById(R.id.project_detail_image);
        circleDisplay = findViewById(R.id.circularDetailDisplay);


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
                Picasso.with(getApplicationContext()).
                        load(AppUtils.Image_BASE_URL + project.getImage_url()).into(project_image);


                description_appCompatTextView.setText("Created by " + project.getUser().getName());
                longer_description_appCompatTextView.setText(project.getProject_description());
                amount_raised__appCompatTextView.setText(String.valueOf(project.getAmount_deposited()));
                amount_targetted__appCompatTextView.setText(String.valueOf(project.getTarget_investment()));
                total_investors__appCompatTextView.setText(String.valueOf(project.getMembers_subscribed()));
                total_investors__textViewDesc.setText(String.valueOf(project.getProject_details()));

                seekBar.setMax(project.getTarget_investment());
                seekBar.setProgress(project.getAmount_deposited());


                //binding the data with the viewholder views
               
                circleDisplay.setAnimDuration(2000);
                circleDisplay.setValueWidthPercent(35f);
                circleDisplay.setTextSize(10f);
                circleDisplay.setColor(R.color.colorAccent);
                circleDisplay.setDrawText(true);
                circleDisplay.setDrawInnerCircle(true);
                circleDisplay.setFormatDigits(1);
                circleDisplay.setTouchEnabled(true);
                circleDisplay.setUnit("%");
                circleDisplay.setStepSize(0.7f);
                circleDisplay.setUnit("%");
                circleDisplay.setStepSize(0.7f);
                // cd.setCustomText(...); // sets a custom array of text
                circleDisplay.showValue((float) project.getAmount_deposited() / (float) project.getTarget_investment() * 100, 100f, true);


            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                Log.e("FAILED", t.toString());
            }
        });


    }


}
