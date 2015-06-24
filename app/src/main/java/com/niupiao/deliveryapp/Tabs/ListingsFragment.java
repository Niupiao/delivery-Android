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
import android.widget.TextView;

import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.Deliveries.DeliveryFragment;
import com.niupiao.deliveryapp.Deliveries.DeliveryPagerActivity;
import com.niupiao.deliveryapp.R;

import java.util.ArrayList;

/**
 * Created by Inanity on 6/22/2015.
 */
public class ListingsFragment extends ListFragment {

    private ArrayList<Delivery> mDeliveries;
    public ArrayList<Delivery> curList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeliveries = DataSource.get(getActivity()).getDeliveries();
        curList = mDeliveries;

        DeliveryAdapter adapter = new DeliveryAdapter(mDeliveries);
        setListAdapter(adapter);
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

        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Delivery d = ((DeliveryAdapter) getListAdapter()).getItem(position);

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
            bounty.setText("$" + d.getBounty());

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
}
