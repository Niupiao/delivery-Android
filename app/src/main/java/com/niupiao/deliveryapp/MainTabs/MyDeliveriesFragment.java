package com.niupiao.deliveryapp.MainTabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niupiao.deliveryapp.R;

/**
 * Created by Inanity on 6/22/2015.
 */
public class MyDeliveriesFragment extends android.support.v4.app.Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_my_tasks_tab, container, false);

        return v;
    }
}
