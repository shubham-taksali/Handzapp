package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tabDescriptionText;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabDescriptionText = findViewById(R.id.tab_textview);
        tabDescriptionText.setText(getString(
                R.string.tab_description_text, getString(R.string.string_remove)));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position == 0) {
                    tabDescriptionText.setText(getString(
                            R.string.tab_description_text, getString(R.string.string_remove)));
                } else if (position == 1) {
                    tabDescriptionText.setText(getString(
                            R.string.tab_description_text, getString(R.string.string_unfavourite)));
                } else if (position == 2) {
                    tabDescriptionText.setText(getString(
                            R.string.tab_description_text, getString(R.string.string_unblock)));
                } else
                    tabDescriptionText.setText(getString(
                            R.string.invalid_selection));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_help) {
            Intent intent = new Intent(MainActivity.this, SafetyTipsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
