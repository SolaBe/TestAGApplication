package com.example.sola2be.testagapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import java.util.List;

public class SwipeActivity extends AppCompatActivity implements ViewPagerInterface,ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private PagerPresenter pagerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerPresenter = new PagerPresenter(this);
    }

    @Override
    public void setList(List<HeroModel> list) {
        viewPager.setAdapter(new HeroesPageAdapter(this,list));
    }

    @Override
    public void setPage(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
