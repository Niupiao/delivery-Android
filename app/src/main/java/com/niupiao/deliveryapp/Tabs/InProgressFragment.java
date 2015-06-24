package com.niupiao.deliveryapp.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.Deliveries.DeliveryFragment;
import com.niupiao.deliveryapp.Deliveries.DeliveryPagerActivity;
import com.niupiao.deliveryapp.R;

import java.util.ArrayList;

/**
 * Created by Inanity on 6/22/2015.
 */
public class InProgressFragment extends ListFragment {

    private ArrayList<Delivery> mInProgress;
    public ArrayList<Delivery> curList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInProgress = DataSource.get(getActivity()).getInProgress();
        curList = mInProgress;

        DeliveryAdapter adapter = new DeliveryAdapter(mInProgress);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_in_progress, container, false);

        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_in_progress);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Fetch new data here
                ((DeliveryAdapter) getListAdapter()).notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Delivery d = ((DeliveryAdapter) getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), DeliveryPagerActivity.class);
        i.putExtra(DeliveryFragment.EXTRA_DELIVERY_ID, d.getId());
        startActivity(i);
    }

    private class DeliveryAdapter extends ArrayAdapter<Delivery> {

        public DeliveryAdapter(ArrayList<Delivery> deliveries) {
            super(getActivity(), 0, deliveries);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_view_current, null);
            }

            Delivery d = getItem(position);
            // initialize list item view

            return convertView;
        }
    }
}
