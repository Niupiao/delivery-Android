package com.niupiao.deliveryapp.SlidingTab;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.R;
import com.niupiao.deliveryapp.Tabs.InProgressFragment;
import com.niupiao.deliveryapp.Tabs.ListingsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Inanity on 6/22/2015.
 */
public class MainTabActivity extends ActionBarActivity {

    ViewPager mViewPager;
    ViewPagerAdapter mAdapter;
    PagerSlidingTabStrip tabs;
    CharSequence titles[] = {"Listings", "Current", "Map"};
    int numTabs = 3;
    ArrayList<Delivery> mCurrentList;
    ListFragment curFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        getSupportActionBar().setElevation(6);
        getSupportActionBar().setTitle("Delivery");

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titles, numTabs);

        mViewPager = (ViewPager) findViewById(R.id.delivery_list_viewPager);
        mViewPager.setAdapter(mAdapter);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setIndicatorColor(getResources().getColor(R.color.ColorPrimaryDark));
        tabs.setShouldExpand(true);
        tabs.setUnderlineHeight(1);
        tabs.setUnderlineColor(getResources().getColor(R.color.ColorPrimaryDark));
        tabs.setTextColorResource(R.color.selector);
        tabs.setDividerColor(getResources().getColor(R.color.material_blue_grey_800));

        tabs.setViewPager(mViewPager);

        final FloatingActionMenu sortMenu = (FloatingActionMenu) findViewById(R.id.menu_sort);
        sortMenu.setClosedOnTouchOutside(true);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    sortMenu.hideMenuButton(true);
                } else {
                    curFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:"
                            + R.id.delivery_list_viewPager + ":" + mViewPager.getCurrentItem());
                    if (position == 0) {
                        mCurrentList = ((ListingsFragment) curFragment).curList;
                    } else {
                        mCurrentList = ((InProgressFragment) curFragment).curList;
                    }
                    sortMenu.showMenuButton(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final FloatingActionButton bountySort = (FloatingActionButton) findViewById(R.id.menu_item_sort_bounty);
        bountySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mCurrentList, new Comparator<Delivery>() {
                    public int compare(Delivery d1, Delivery d2) {
                        if (d1.getBounty() < d2.getBounty())
                            return 1;
                        if (d1.getBounty() > d2.getBounty())
                            return -1;
                        else
                            return 0;
                    }
                });

                ((ArrayAdapter) curFragment.getListAdapter()).notifyDataSetChanged();
                sortMenu.close(true);
            }
        });

        final FloatingActionButton timeSort = (FloatingActionButton) findViewById(R.id.menu_item_sort_time);
        timeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortMenu.close(true);
            }
        });

        final FloatingActionButton distanceSort = (FloatingActionButton) findViewById(R.id.menu_item_sort_distance);
        distanceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortMenu.close(true);
            }
        });
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
