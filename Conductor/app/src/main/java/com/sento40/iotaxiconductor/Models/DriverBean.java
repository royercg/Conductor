package com.sento40.iotaxiconductor.Models;

import com.google.firebase.firestore.Exclude;

/**
 * @author RC - May 08, 2019
 * @version 0.1.0
 * @since 0.1.0
 *
 * This activity represents a form by notifications
 */
public class DriverBean {

    @Exclude
    private String uuid;
    private String name;
    private String lastName;
    private String fcmKey;
    private String phoneNumber;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFcmKey() {
        return fcmKey;
    }

    public void setFcmKey(String fcmKey) {
        this.fcmKey = fcmKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
