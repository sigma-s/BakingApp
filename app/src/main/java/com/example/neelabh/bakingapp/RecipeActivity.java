package com.example.neelabh.bakingapp;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {
    @BindView(R.id.recipe_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.recipe_ingredients) TextView mTextView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<String> data;
    private String htmlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_main);
        ButterKnife.bind(this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Toolbar mToolbar = findViewById(R.id.toolbar_recipe);
        setSupportActionBar(mToolbar);

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        data = new ArrayList<String>();
        for(int i=0;i<MyData.recipeStepsArray.length;i++){
            data.add(MyData.recipeStepsArray[i]);
        }

        htmlString = "Ingredients:<br/>";

        for(int i=0;i<MyData.ingredientArray.length;i++){
            htmlString = htmlString + "- "+ MyData.ingredientArray[i] + "<br/>";
        }

        mTextView.setText(Html.fromHtml(htmlString));

        mAdapter = new RecipeAdapter(this, data);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL){
                    @Override
                    public void getItemOffsets(Rect outRect, View view,RecyclerView parent,RecyclerView.State state){
                        int position = parent.getChildAdapterPosition(view);
                        if(position==state.getItemCount()-1) {
                            outRect.setEmpty();
                        }else{
                            super.getItemOffsets(outRect,view,parent,state);
                        }
                    }
                });
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


}
