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
    public static String USER_KEY = "a";

    private ArrayList<Delivery> mDeliveries;
    private ArrayList<Delivery> mInProgress;

    private DataSource(Context appContext) {
        mAppContext = appContext;
        mInProgress = new ArrayList<Delivery>();
        mDeliveries = new ArrayList<Delivery>();
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
        for (Delivery d : mInProgress) {
            if (d.getId().equals(id))
                return d;
        }
        for (Delivery d : mDeliveries) {
            if (d.getId().equals(id))
                return d;
        }
        return null;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        mDeliveries = deliveries;
    }

    public void setInProgress(ArrayList<Delivery> inProgress) {
        mInProgress = inProgress;
    }

    public void claimDelivery(Delivery d) {
        for (int i = 0; i < mDeliveries.size(); i++) {
            if (mDeliveries.get(i).equals(d)) {
                Delivery claimed = mDeliveries.remove(i);
                claimed.setClaimed(true);
                mInProgress.add(claimed);
            }
        }
    }
}
