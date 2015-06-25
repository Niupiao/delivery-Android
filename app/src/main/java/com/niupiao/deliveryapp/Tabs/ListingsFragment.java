package com.niupiao.deliveryapp.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.Deliveries.DeliveryFragment;
import com.niupiao.deliveryapp.Deliveries.DeliveryPagerActivity;
import com.niupiao.deliveryapp.R;
import com.niupiao.deliveryapp.SlidingTab.MainTabActivity;

import java.util.ArrayList;

/**
 * Created by Inanity on 6/22/2015.
 */
public class ListingsFragment extends ListFragment {

    private ArrayList<Delivery> mDeliveries;
    public ArrayList<Delivery> curList;
    private DeliveryAdapter mAdapter;
    int mLastFirstVisibleItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDeliveries = DataSource.get(getActivity()).getDeliveries();
        curList = mDeliveries;

        mAdapter = new DeliveryAdapter(mDeliveries);
        setListAdapter(mAdapter);

        ((MainTabActivity) getActivity()).setCurrentList(mAdapter, mDeliveries);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listings, container, false);

        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh_listings_view);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Fetch new data here
                ((DeliveryAdapter) getListAdapter()).notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });

        final FloatingActionMenu sortMenu = (FloatingActionMenu) getActivity().findViewById(R.id.menu_sort);
        final ListView lv = (ListView) v.findViewById(android.R.id.list);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final int currentFirstVisibleItem = lv.getFirstVisiblePosition();

                if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                    // Scrolling down
                    //sortMenu.animate().cancel();
                    //sortMenu.animate().translationYBy(350);
                } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                    // Scrolling up
                    //sortMenu.animate().cancel();
                    //sortMenu.animate().translationYBy(-350);
                }

                mLastFirstVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Delivery d = mAdapter.getItem(position);

        Intent i = new Intent(getActivity(), DeliveryPagerActivity.class);
        i.putExtra(DeliveryFragment.EXTRA_DELIVERY_ID, d.getId());
        startActivity(i);
    }

    public class DeliveryAdapter extends ArrayAdapter<Delivery> {

        public DeliveryAdapter(ArrayList<Delivery> deliveries) {
            super(getActivity(), 0, deliveries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_view_delivery, null);
            }

            Delivery d = getItem(position);
            TextView bounty = (TextView) convertView.findViewById(R.id.list_item_bounty);
            bounty.setText("$" + d.getWage());
            TextView puTime = (TextView) convertView.findViewById(R.id.list_item_pickup_time);
            puTime.setText(d.getPickupTime() + " - " + (d.getPickupTime() + 3) + " AM");
            TextView puDistance = (TextView) convertView.findViewById(R.id.list_item_distance);
            puDistance.setText(d.mDistance + " km");


            View statusColorView = convertView.findViewById(R.id.priority_indicator);
            switch (d.getDeliveryStatus()) {
                case Delivery.READY_FOR_PICKUP:
                    statusColorView.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
                    break;
                case Delivery.PICKED_UP:
                    statusColorView.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
                    break;
                case Delivery.DELIVERED:
                    statusColorView.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryDark));
                    break;
            }

            return convertView;
        }
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem spinner = menu.findItem(R.id.sort_spinner);
        Spinner sortSpinner = (Spinner)MenuItemCompat.getActionView(spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.sort_items,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // Sort by distance
                        break;
                    case 1:
                        // Sort by time
                        break;
                    case 2:
                        Collections.sort(mDeliveries, new Comparator<Delivery>() {
                            public int compare(Delivery d1, Delivery d2) {
                                if (d1.getWage() < d2.getWage())
                                    return 1;
                                if (d1.getWage() > d2.getWage())
                                    return -1;
                                else
                                    return 0;
                            }
                        });

                        ((DeliveryAdapter)getListAdapter()).notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
    */
}
