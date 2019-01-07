package com.example.neelabh.bakingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private ArrayList<String> mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public MyViewHolder(View v){
            super(v);
            mTextView = v.findViewById(R.id.recipe_step_text);
        }
    }

    public RecipeAdapter(ArrayList<String> myDataset){
        mDataset = myDataset;
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

}
