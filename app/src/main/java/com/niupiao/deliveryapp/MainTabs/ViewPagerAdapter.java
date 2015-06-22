package com.niupiao.deliveryapp.MainTabs;

import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Inanity on 6/22/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public CharSequence Titles[];
    public int numTabs;

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm, CharSequence mTitles[], int mNumTabs) {
        super(fm);

        this.Titles = mTitles;
        this.numTabs = mNumTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            DeliveriesFragment dF = new DeliveriesFragment();
            return dF;
        }
        if (position == 1) {
            MyDeliveriesFragment mDF = new MyDeliveriesFragment();
            return mDF;
        }
        if (position == 2) {
            MapFragment mF = new MapFragment();
            return mF;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
