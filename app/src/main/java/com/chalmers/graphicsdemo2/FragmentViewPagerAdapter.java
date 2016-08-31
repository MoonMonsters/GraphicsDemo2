package com.chalmers.graphicsdemo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Chalmers on 2016-08-30 19:41.
 * email:qxinhai@yeah.net
 */
public class FragmentViewPagerAdapter extends FragmentPagerAdapter {

    String titles[] = {"折线图", "饼状图", "弧线图", "柱状图"};
    private ArrayList<Fragment> mFragments;

    public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
