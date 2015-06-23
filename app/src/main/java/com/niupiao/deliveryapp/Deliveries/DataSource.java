package com.niupiao.deliveryapp.Deliveries;

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
    private ArrayList<Delivery> mInProgress;

    private DataSource(Context appContext) {
        mAppContext = appContext;
        mInProgress = new ArrayList<Delivery>();
        mDeliveries = new ArrayList<Delivery>();
        for (int i = 1; i < 11; i++) {
            Delivery d = new Delivery(i + "");
            mDeliveries.add(d);
        }

        for (int i = 1; i < 4; i++) {
            Delivery d = new Delivery(11 + i + "");
            mInProgress.add(d);
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

    public ArrayList<Delivery> getInProgress() {
        return mInProgress;
    }

    public Delivery getDelivery(UUID id) {
        for (Delivery d : mDeliveries) {
            if (d.getId().equals(id))
                return d;
        }
        for (Delivery d : mInProgress) {
            if (d.getId().equals(id))
                return d;
        }
        return null;
    }
}
