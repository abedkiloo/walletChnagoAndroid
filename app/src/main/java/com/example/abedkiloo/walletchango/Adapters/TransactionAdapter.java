package com.example.abedkiloo.walletchango.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abedkiloo.walletchango.Activities.ProjectDetails;
import com.example.abedkiloo.walletchango.DataModel.Transactions;
import com.example.abedkiloo.walletchango.Helpers.CircleDisplay;
import com.example.abedkiloo.walletchango.R;

import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context mCtx;

    //we are storing all the transactionsss in a list
    private List<Transactions> transactionsList;

    //getting the context and transactions list with constructor
    public TransactionAdapter(Context mCtx, List<Transactions> transactionsList) {
        this.mCtx = mCtx;
        this.transactionsList = transactionsList;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.transactions_design, null);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        //getting the transactions of the specified position
        final Transactions transactions = transactionsList.get(position);



    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }


    class TransactionViewHolder extends RecyclerView.ViewHolder {




        public TransactionViewHolder(View itemView) {
            super(itemView);


        }
    }
}
