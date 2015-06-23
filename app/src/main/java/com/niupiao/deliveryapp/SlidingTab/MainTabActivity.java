package com.niupiao.deliveryapp.SlidingTab;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.niupiao.deliveryapp.R;

/**
 * Created by Inanity on 6/22/2015.
 */
public class MainTabActivity extends ActionBarActivity {

    ViewPager mViewPager;
    ViewPagerAdapter mAdapter;
    PagerSlidingTabStrip tabs;
    CharSequence titles[] = {"Listings", "Current", "Map"};
    int numTabs = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numTabs);

        mViewPager = (ViewPager) findViewById(R.id.delivery_list_viewPager);
        mViewPager.setAdapter(mAdapter);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setIndicatorColor(getResources().getColor(R.color.tabsScrollColor));
        tabs.setShouldExpand(true);
        tabs.setUnderlineHeight(2);
        tabs.setUnderlineColor(getResources().getColor(R.color.ColorPrimaryDark));
        tabs.setTextColorResource(R.color.selector);
        tabs.setDividerColor(getResources().getColor(R.color.material_blue_grey_800));
        /*
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        */

        tabs.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
