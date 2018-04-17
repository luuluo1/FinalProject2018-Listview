package com.example.oliver.finalproject2018;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


public class add_opt_p extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opt_p);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.a_opt_holder,new opt_p_Frag());
        ft.commit();
    }
}
