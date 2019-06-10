package com.example.abedkiloo.walletchango.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abedkiloo.walletchango.Activities.ProjectDetails;
import com.example.abedkiloo.walletchango.DataModel.Projects;
import com.example.abedkiloo.walletchango.Helpers.AppUtils;
import com.example.abedkiloo.walletchango.Helpers.CircleDisplay;
import com.example.abedkiloo.walletchango.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private Context mCtx;

    //we are storing all the projectss in a list
    private List<Projects> projectsList;

    //getting the context and projects list with constructor
    public ProjectAdapter(Context mCtx, List<Projects> projectsList) {
        this.mCtx = mCtx;
        this.projectsList = projectsList;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.projects_design, null);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        //getting the projects of the specified position
        final Projects projects = projectsList.get(position);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        String red = String.valueOf(projects.getTarget_investment());
        SpannableString redSpannable = new SpannableString(red);
        redSpannable.setSpan(new ForegroundColorSpan(mCtx.getResources().getColor(R.color.colorAccent)), 0, red.length(), 0);
        builder.append("Collected " + String.valueOf(projects.getAmount_deposited()) + " of ");
        builder.append(redSpannable);


        //binding the data with the viewholder views
        holder.textViewTitle.setText(projects.getProject_name());
        holder.tvAmount.setText(builder, TextView.BufferType.SPANNABLE);
        holder.circleDisplay.setAnimDuration(2000);
        holder.circleDisplay.setValueWidthPercent(35f);
        holder.circleDisplay.setTextSize(10f);
        holder.circleDisplay.setColor(R.color.colorAccent);
        holder.circleDisplay.setDrawText(true);
        holder.circleDisplay.setDrawInnerCircle(true);
        holder.circleDisplay.setFormatDigits(1);
        holder.circleDisplay.setTouchEnabled(true);
        holder.circleDisplay.setSelectionListener(new CircleDisplay.SelectionListener() {
            @Override
            public void onSelectionUpdate(float val, float maxval) {

            }

            @Override
            public void onValueSelected(float val, float maxval) {

            }
        });
        Picasso.with(mCtx).
                load(AppUtils.Image_BASE_URL + projects.getImage_url()).into(holder.project_image);
        holder.circleDisplay.setUnit("%");
        holder.circleDisplay.setStepSize(0.7f);
        // cd.setCustomText(...); // sets a custom array of text
        holder.circleDisplay.showValue((float) projects.getAmount_deposited() / (float) projects.getTarget_investment() * 100, 100f, true);
        holder.tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent project_details_intent = new Intent(mCtx, ProjectDetails.class);
                project_details_intent.putExtra("project_id", projects.getId());
                mCtx.startActivity(project_details_intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return projectsList.size();
    }


    class ProjectViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, tvView, tvAmount;
        CircleDisplay circleDisplay;
        AppCompatImageView project_image;


        public ProjectViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            tvView = itemView.findViewById(R.id.tView);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            circleDisplay = itemView.findViewById(R.id.circularDisplay);
            project_image = itemView.findViewById(R.id.project_image);
        }
    }
}
