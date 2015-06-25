package com.niupiao.deliveryapp.Deliveries;

import java.sql.Time;
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
    public int Wage;

    public String mPickupName;
    public String mPickupAddress;
    public Time mPickupTime;
    public String mPickupPhone;

    public String mDropoffName;
    public String mDropoffAddress;
    public String mDropoffTime;
    public String mDropoffPhone;
    public int mDeliveryStatus;

    public Delivery(String name, int bounty) {
        mId = UUID.randomUUID();
        mName = "Delivery " + name;
        Wage = bounty;
        mPickupAddress = "Street Name";
        mDeliveryStatus = bounty % 3;
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
        return Wage;
    }

    public void setWage(int wage) {
        Wage = wage;
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

    public Time getPickupTime() {
        return mPickupTime;
    }

    public void setPickupTime(Time pickupTime) {
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

    public String getDropoffTime() {
        return mDropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
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
