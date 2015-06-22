package com.niupiao.deliveryapp.Listings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.niupiao.deliveryapp.DataSource;
import com.niupiao.deliveryapp.Delivery;
import com.niupiao.deliveryapp.DeliveryFragment;
import com.niupiao.deliveryapp.DeliveryPagerActivity;
import com.niupiao.deliveryapp.R;

import java.util.ArrayList;

/**
 * Created by Inanity on 6/22/2015.
 */
public class ListingsFragment extends ListFragment {

    private ArrayList<Delivery> mDeliveries;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeliveries = DataSource.get(getActivity()).getDeliveries();


        DeliveryAdapter adapter = new DeliveryAdapter(mDeliveries);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listings, container, false);
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
                        .inflate(R.layout.delivery_list_view, null);
            }

            Delivery d = getItem(position);
            // initialize list item view
            TextView nameTv = (TextView) convertView.findViewById(R.id.list_item_name);
            nameTv.setText(d.mName);

            return convertView;
        }
    }
}
