package com.example.ericxu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.ericxu.ui.FirstFragment;

import java.util.List;

/**
 * Created by ericxu on 15/3/26.
 */
public class SlideViewPagerAdapter extends FragmentPagerAdapter {

    public SlideViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FirstFragment.newInstance(String.valueOf(position), "");
    }

    @Override
    public int getCount() {
        return 4;
    }
}
