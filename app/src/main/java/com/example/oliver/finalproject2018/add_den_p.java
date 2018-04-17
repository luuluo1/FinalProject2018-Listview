package com.example.oliver.finalproject2018;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class add_den_p extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_den_p);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.add_fragment_holder, new den_p_Frag());
        ft.commit();
    }
}
