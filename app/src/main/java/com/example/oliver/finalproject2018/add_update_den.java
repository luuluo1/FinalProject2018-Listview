package com.example.oliver.finalproject2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class add_update_den extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_den);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id=getIntent().getExtras().getInt("EXTRA_SESSION_ID");

        update_den udd=new update_den();


        udd.setdenId(id);
     //   ft.replace(R.id.den_u_holder, udd);
        ft.commit();
    }
}
