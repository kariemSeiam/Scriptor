package com.scriptor.scriptor2020.cpanel;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.scriptor.scriptor2020.PagerAdapter;
import com.scriptor.scriptor2020.R;

public class CPanel extends FragmentActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_panel);
        findViewById();
        setUpTabLayout();
    }

    void findViewById() {
        tabLayout = findViewById(R.id.tab_layout_CP);
        viewPager = findViewById(R.id.viewpagerCP);
    }

    @SuppressLint("ResourceAsColor")
    void setUpTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Admins Quotes"));
        tabLayout.addTab(tabLayout.newTab().setText("Users Quotes"));
        tabLayout.addTab(tabLayout.newTab().setText("Users Novels"));
        tabLayout.addTab(tabLayout.newTab().setText("Users Short Stories"));
        tabLayout.addTab(tabLayout.newTab().setText("FeedBacks"));
        tabLayout.addTab(tabLayout.newTab().setText("Profiles"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setBackgroundColor(Color.BLACK);
        PagerCPanelAdapter pagerAdapter = new PagerCPanelAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}