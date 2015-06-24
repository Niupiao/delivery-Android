package com.niupiao.deliveryapp.Deliveries;

import java.sql.Time;
import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class Delivery {

    public UUID mId;
    public String mName;
    public int mBounty;

    public String mPickupName;
    public String mPickupAddress;
    public Time mPickupTime;
    public String mPickupPhone;

    public String mDropoffName;
    public String mDropoffAddress;
    public String mDropoffTime;
    public String mDropoffPhone;

    public Delivery(String name, int bounty) {
        mId = UUID.randomUUID();
        mName = "Delivery " + name;
        mBounty = bounty;
        mPickupAddress = "Street Name";
        /*
        mPickupName;
        mPickupTime;
        mPickupPhone;

        mDropoffName;
        mDropoffAddress;
        mDropoffTime;
        mDropoffPhone;
        */
    }

    @Override
    public String toString() {
        return mName;
    }

    public UUID getId() {
        return mId;
    }
}
