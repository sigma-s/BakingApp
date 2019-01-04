package com.example.neelabh.bakingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_title)
        TextView cardTitle;
        @BindView(R.id.card_image)
        ImageView cardImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.cardTitle=itemView.findViewById(R.id.card_title);
            this.cardImage=itemView.findViewById(R.id.card_image);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data){
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
        //view.setOnClickListener(MainActivity.myOnClickListener);
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

}
