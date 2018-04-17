package com.example.oliver.finalproject2018;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class add_update_den extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_den);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.add_fragment_holder, new update_den());
        ft.commit();
    }
}
