package com.example.neelabh.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.Locale;


public class RecipeStepSliderActivity extends FragmentActivity {
    private static final int NUM_PAGES = MyData.recipeStepsArray.length;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private String tabTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step_screen_slide);
        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        TabLayout mTabLayout = findViewById(R.id.recipe_step_tablayout);
        mTabLayout.setupWithViewPager(mPager);
    }

    @Override
    public void onBackPressed(){
        if(mPager.getCurrentItem()==0){
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem()-1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm){

            super(fm);
            tabTitle = getString(R.string.recipe_step_label);
        }

        @Override
        public Fragment getItem(int position){
            return new RecipeStepFragment();
        }

        @Override
        public int getCount(){
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return String.format(Locale.US,tabTitle,position);
        }
    }

}
