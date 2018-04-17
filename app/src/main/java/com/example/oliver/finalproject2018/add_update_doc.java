package com.example.oliver.finalproject2018;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class add_update_doc extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_doc);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.f_holder,new update_doc());
        ft.commit();
    }
}
