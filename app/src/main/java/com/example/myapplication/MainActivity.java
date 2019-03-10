package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabFragment tabFragment;
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
                } else {
                    tabDescriptionText.setText(getString(
                            R.string.invalid_selection));
                }
                invalidateOptionsMenu();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabFragment = (TabFragment) viewPager.getAdapter().
                        instantiateItem(viewPager, viewPager.getCurrentItem());
                tabFragment.returnToNormalMode();
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
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionButton = menu.findItem(R.id.action_remove);
        MenuItem actionHelp = menu.findItem(R.id.action_help);
        if (MyApplication.isInMultiSelectMode()) {
            actionHelp.setVisible(false);
            actionButton.setVisible(true);
            if (position == 0)
                actionButton.setTitle(R.string.action_remove);
            else if (position == 1)
                actionButton.setTitle(R.string.action_unfavorite);
            else if (position == 2)
                actionButton.setTitle(R.string.action_unblock);
        } else {
            actionHelp.setVisible(true);
            actionButton.setVisible(false);
        }

        if (MyApplication.isInMultiSelectMode() && MyApplication.getSelectedItemCount() == 0) {
            getSupportActionBar().setTitle(R.string.app_name);
            actionButton.setEnabled(false);
        }
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
        if (id == R.id.action_remove) {
            return false;
        }
        if (id == android.R.id.home) {
            //Can be used to go to previous screen or to close the current activity
        }

        return super.onOptionsItemSelected(item);
    }
}
