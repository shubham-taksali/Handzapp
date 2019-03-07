package com.example.myapplication;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ViewPagerAdapter extends FragmentPagerAdapter {

    private Activity mainActivity;
    private String title[];

    public ViewPagerAdapter(FragmentManager manager, Activity activity) {
        super(manager);
        this.mainActivity = activity;
        title = new String[]{mainActivity.getString(R.string.tab_one),
                                    mainActivity.getString(R.string.tab_two),
                                    mainActivity.getString(R.string.tab_three)};
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}