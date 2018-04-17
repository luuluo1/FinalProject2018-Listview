package com.example.oliver.finalproject2018.dummy;

import java.util.Date;

/**
 * Created by oliver on 4/11/2018.
 */

public class Opt_patient extends Patient{


    private String glassesBought;
    private String glassesStore;
    private int ID;
    public Opt_patient() {

    }

    public Opt_patient(String glassesBought, String glassesStore) {

        this.glassesBought = glassesBought;
        this.glassesStore = glassesStore;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setGlassesBought(String glassesBought) {
        this.glassesBought = glassesBought;
    }

    public void setGlassesStore(String glassesStore) {
        this.glassesStore = glassesStore;
    }

    public String getGlassesBought() {
        return glassesBought;
    }

    public String getGlassesStore() {
        return glassesStore;
    }
}
