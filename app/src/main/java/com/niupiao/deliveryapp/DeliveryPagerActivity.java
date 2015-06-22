package com.niupiao.deliveryapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class DeliveryPagerActivity extends ActionBarActivity {
    private ViewPager mViewPager;
    private ArrayList<Delivery> mDeliveries;
    android.support.v7.widget.Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_viewpager);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mDeliveries = DataSource.get(this).getDeliveries();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Delivery d = mDeliveries.get(position);
                return DeliveryFragment.newInstance(d.getId());
            }

            @Override
            public int getCount() {
                return mDeliveries.size();
            }
        });

        UUID id = (UUID) getIntent().getSerializableExtra(DeliveryFragment.EXTRA_DELIVERY_ID);
        for (int i = 0; i < mDeliveries.size(); i++) {
            if (mDeliveries.get(i).getId().equals(id)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Delivery d = mDeliveries.get(position);
                if (d.mName != null)
                    setTitle(d.mName);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
