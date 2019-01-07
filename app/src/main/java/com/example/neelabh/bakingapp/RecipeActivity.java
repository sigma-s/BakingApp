package com.example.neelabh.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {
    @butterknife.BindView(R.id.recipe_recycler_view) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_main);
        ButterKnife.bind(this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        data = new ArrayList<String>();
        for(int i=0;i<MyData.recipeStepsArray.length;i++){
            data.add(MyData.recipeStepsArray[i]);
        }

        mAdapter = new RecipeAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

    }
}
