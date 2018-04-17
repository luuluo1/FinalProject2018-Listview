package com.example.oliver.finalproject2018.dummy;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by oliver on 4/11/2018.
 */

public class Doc_patient extends Patient implements Serializable{
    public static final String TAG="Doc_patient";
    public static final long serialVersionUID=7406082437623008161L;

    private long mID;
    private String name;
    private String address;
    private String birthday;
    private String phoneNumber;
    private String HealthCard;
    private String Description ;

    private String previousSurgery;
    private String allergies;

    public Doc_patient() {

    }

    public Doc_patient(String name) {
        this.name = name;
    }

    public Doc_patient(String name, String address, String birthday, String phoneNumber, String healthCard, String description, String previousSurgery, String allergies) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        HealthCard = healthCard;
        Description = description;
        this.previousSurgery = previousSurgery;
        this.allergies = allergies;
    }

    public void setmID(long mID) {
        this.mID = mID;
    }

    public long getmID() {
        return mID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHealthCard() {
        return HealthCard;
    }

    public String getDescription() {
        return Description;
    }

    public String getPreviousSurgery() {
        return previousSurgery;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHealthCard(String healthCard) {
        HealthCard = healthCard;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPreviousSurgery(String previousSurgery) {
        this.previousSurgery = previousSurgery;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
