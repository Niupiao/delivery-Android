package com.niupiao.deliveryapp.Deliveries;

import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class Delivery {

    public String mName;
    public String mAddress;
    public UUID mId;

    public Delivery(String name) {
        mName = "Delivery " + name;
        mAddress = "Street Name";
        mId = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return mName;
    }

    public UUID getId() {
        return mId;
    }
}
