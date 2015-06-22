package com.niupiao.deliveryapp.Listings;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.niupiao.deliveryapp.Delivery;
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
        mDeliveries = new ArrayList<Delivery>();
        for (int i = 0; i < 10; i++) {
            Delivery d = new Delivery(i + "");
            mDeliveries.add(d);
        }

        ArrayAdapter<Delivery> adapter = new ArrayAdapter<Delivery>(getActivity(), android.R.layout.simple_list_item_1, mDeliveries);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listings, container, false);
        return v;
    }
}
