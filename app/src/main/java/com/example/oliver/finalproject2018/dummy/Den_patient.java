package com.example.oliver.finalproject2018.dummy;

import java.util.Date;

/**
 * Created by oliver on 4/11/2018.
 */

public class Den_patient extends Patient {


    private String Benifits;
    private String hadBraces;

    public Den_patient() {

    }

    public Den_patient( String benifits, String hadBraces) {

        this.Benifits = benifits;
        this.hadBraces = hadBraces;
    }



    public void setBenifits(String benifits) {
        Benifits = benifits;
    }

    public void setHadBraces(String hadBraces) {
        this.hadBraces = hadBraces;
    }



    public String isBenifits() {
        return Benifits;
    }

    public String isHadBraces() {
        return hadBraces;
    }
}
