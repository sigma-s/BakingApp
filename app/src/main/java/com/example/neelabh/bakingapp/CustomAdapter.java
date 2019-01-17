package com.example.neelabh.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private static Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_title)
        TextView cardTitle;
        @BindView(R.id.card_image)
        ImageView cardImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //this.cardTitle=itemView.findViewById(R.id.card_title);
            //this.cardImage=itemView.findViewById(R.id.card_image);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MyItemClick(v);
                }
            });
        }
    }

    public CustomAdapter(Context context, ArrayList<DataModel> data){
        this.dataSet = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition){
        TextView cardTitle = holder.cardTitle;
        ImageView cardImage = holder.cardImage;

        cardTitle.setText(dataSet.get(listPosition).getName());
        cardImage.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    private static void MyItemClick(View v){
        Intent mIntent = new Intent(mContext,RecipeActivity.class);
        //Intent mIntent = new Intent(mContext,RecipeStepSliderActivity.class);
        mContext.startActivity(mIntent);
    }

}
