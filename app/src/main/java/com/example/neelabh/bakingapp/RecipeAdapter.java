package com.example.neelabh.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private ArrayList<String> mDataset;
    private static Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public MyViewHolder(View v){
            super(v);
            mTextView = v.findViewById(R.id.recipe_step_text);
            v.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MyItemClick(v);
                }
            });
        }
    }

    public RecipeAdapter(Context context, ArrayList<String> myDataset){

        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_layout,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.mTextView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

    private static void MyItemClick(View v){
        Intent mIntent = new Intent(mContext,RecipeStepSliderActivity.class);
        mContext.startActivity(mIntent);
    }

}
