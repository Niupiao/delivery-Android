package com.niupiao.deliveryapp.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.niupiao.deliveryapp.Deliveries.DataSource;
import com.niupiao.deliveryapp.Deliveries.Delivery;
import com.niupiao.deliveryapp.Deliveries.DeliveryFragment;
import com.niupiao.deliveryapp.Deliveries.DeliveryPagerActivity;
import com.niupiao.deliveryapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Inanity on 6/22/2015.
 */
public class InProgressFragment extends ListFragment {

    private ArrayList<Delivery> mInProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInProgress = DataSource.get(getActivity()).getInProgress();

        DeliveryAdapter adapter = new DeliveryAdapter(mInProgress);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_in_progress, container, false);

        final FloatingActionMenu sortMenu = (FloatingActionMenu) v.findViewById(R.id.current_menu_sort);
        sortMenu.setClosedOnTouchOutside(true);

        final FloatingActionButton bountySort = (FloatingActionButton) v.findViewById(R.id.current_sort_bounty);
        bountySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort by bounty
                Collections.sort(mInProgress, new Comparator<Delivery>() {
                    public int compare(Delivery d1, Delivery d2) {
                        if (d1.getBounty() < d2.getBounty())
                            return 1;
                        if (d1.getBounty() > d2.getBounty())
                            return -1;
                        else
                            return 0;
                    }
                });
                ((DeliveryAdapter) getListAdapter()).notifyDataSetChanged();
                sortMenu.close(true);
            }
        });

        final FloatingActionButton timeSort = (FloatingActionButton) v.findViewById(R.id.current_sort_time);
        timeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mInProgress, new Comparator<Delivery>() {
                    public int compare(Delivery d1, Delivery d2) {
                        if (d1.getBounty() < d2.getBounty())
                            return 1;
                        if (d1.getBounty() > d2.getBounty())
                            return -1;
                        else
                            return 0;
                    }
                });
                ((DeliveryAdapter) getListAdapter()).notifyDataSetChanged();
                sortMenu.close(true);
            }
        });

        final FloatingActionButton distanceSort = (FloatingActionButton) v.findViewById(R.id.current_sort_distance);
        distanceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(mInProgress, new Comparator<Delivery>() {
                    public int compare(Delivery d1, Delivery d2) {
                        if (d1.getBounty() < d2.getBounty())
                            return 1;
                        if (d1.getBounty() > d2.getBounty())
                            return -1;
                        else
                            return 0;
                    }
                });
                ((DeliveryAdapter) getListAdapter()).notifyDataSetChanged();
                sortMenu.close(true);
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
