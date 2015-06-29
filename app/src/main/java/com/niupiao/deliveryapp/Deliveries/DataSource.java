package com.niupiao.deliveryapp.Deliveries;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class DataSource {
    private static DataSource sDataSource;
    private Context mAppContext;

    public static final String MY_DELIVERIES = "JSON my current deliveries array";
    public static final String USER_KEY = "JSON object Key";
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

    private ArrayList<Delivery> mDeliveries;
    private ArrayList<Delivery> mInProgress;

    private DataSource(Context appContext) {
        mAppContext = appContext;
        mInProgress = new ArrayList<Delivery>();
        mDeliveries = new ArrayList<Delivery>();
        for (int i = 1; i < 6; i++) {
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

    public void createLists(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray(MY_DELIVERIES);
            for (int i = 0; i < array.length(); i++) {
                Delivery d = new Delivery(array.getJSONObject(i));
                mInProgress.add(d);
            }
        } catch (JSONException e) {
            Log.e("JSON Array failed", e.toString());
        }
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
