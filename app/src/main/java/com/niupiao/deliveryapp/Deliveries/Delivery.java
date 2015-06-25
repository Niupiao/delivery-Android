package com.niupiao.deliveryapp.Deliveries;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Joseph on 6/22/15.
 */
public class Delivery {

    public static final int READY_FOR_PICKUP = 0;
    public static final int PICKED_UP = 1;
    public static final int DELIVERED = 2;

    public UUID mId;
    public String mName;
    public int mWage;
    public int mDistance;

    public String mPickupName;
    public String mPickupAddress;
    public int mPickupTime;
    public String mPickupPhone;

    public String mDropoffName;
    public String mDropoffAddress;
    public int mDropoffTime;
    public String mDropoffPhone;
    public int mDeliveryStatus;

    public Delivery(String name) {
        mId = UUID.randomUUID();

        mName = "Delivery " + name;
        mWage = new Random().nextInt(5) + 1;
        mPickupAddress = "" + mDropoffTime + mPickupTime + " Hollybrook St";
        mDeliveryStatus = READY_FOR_PICKUP;
        mDistance = new Random().nextInt(11) + 1;

        //mPickupName;
        mPickupTime = new Random().nextInt(4) + 4;
        //mPickupPhone;

        //mDropoffName;
        mDropoffAddress = "" + mWage + mDropoffTime + " Jenkins Rd";
        mDropoffTime = new Random().nextInt(7) + 1;
        //mDropoffPhone;

    }

    @Override
    public String toString() {
        return mName;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getWage() {
        return mWage;
    }

    public void setWage(int wage) {
        mWage = wage;
    }

    public String getPickupName() {
        return mPickupName;
    }

    public void setPickupName(String pickupName) {
        mPickupName = pickupName;
    }

    public String getPickupAddress() {
        return mPickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        mPickupAddress = pickupAddress;
    }

    public int getPickupTime() {
        return mPickupTime;
    }

    public void setPickupTime(int pickupTime) {
        mPickupTime = pickupTime;
    }

    public String getPickupPhone() {
        return mPickupPhone;
    }

    public void setPickupPhone(String pickupPhone) {
        mPickupPhone = pickupPhone;
    }

    public String getDropoffName() {
        return mDropoffName;
    }

    public void setDropoffName(String dropoffName) {
        mDropoffName = dropoffName;
    }

    public String getDropoffAddress() {
        return mDropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        mDropoffAddress = dropoffAddress;
    }

    public int getDropoffTime() {
        return mDropoffTime;
    }

    public void setDropoffTime(int dropoffTime) {
        mDropoffTime = dropoffTime;
    }

    public String getDropoffPhone() {
        return mDropoffPhone;
    }

    public void setDropoffPhone(String dropoffPhone) {
        mDropoffPhone = dropoffPhone;
    }

    public int getDeliveryStatus() {
        return mDeliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        mDeliveryStatus = deliveryStatus;
    }

    public UUID getId() {
        return mId;
    }
}
