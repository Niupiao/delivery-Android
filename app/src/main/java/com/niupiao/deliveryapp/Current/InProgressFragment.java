package com.niupiao.deliveryapp.Current;

import android.support.v4.app.Fragment;

/**
 * Created by Inanity on 6/22/2015.
 */
public class InProgressFragment extends Fragment {
/*
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
            //nameTv.setText(d.mName);

            return convertView;
        }
    }
    */
}
