package com.niupiao.deliveryapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class DataSource {
    private static DataSource sDataSource;
    private Context mAppContext;

    private ArrayList<Delivery> mDeliveries;

    private DataSource(Context appContext) {
        mAppContext = appContext;
        mDeliveries = new ArrayList<Delivery>();
        for (int i = 1; i < 11; i++) {
            Delivery d = new Delivery(i + "");
            mDeliveries.add(d);
        }
    }

    public static DataSource get(Context c) {
        if (sDataSource == null) {
            sDataSource = new DataSource(c.getApplicationContext());
        }
        return sDataSource;
    }

    public ArrayList<Delivery> getDeliveries() {
        return mDeliveries;
    }

    public Delivery getDelivery(UUID id) {
        for (Delivery d : mDeliveries) {
            if (d.getId().equals(id))
                return d;
        }
        return null;
    }
}
