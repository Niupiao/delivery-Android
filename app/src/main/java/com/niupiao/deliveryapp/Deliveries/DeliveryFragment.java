package com.niupiao.deliveryapp.Deliveries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.niupiao.deliveryapp.R;

import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class DeliveryFragment extends Fragment {

    public static final String EXTRA_DELIVERY_ID = "Delivery ID";

    private TextView mTitleTextView;
    private Delivery mDelivery;

    public static DeliveryFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DELIVERY_ID, id);

        DeliveryFragment frag = new DeliveryFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID id = (UUID) getArguments().getSerializable(EXTRA_DELIVERY_ID);
        mDelivery = DataSource.get(getActivity()).getDelivery(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delivery, parent, false);

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null)
                    NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}