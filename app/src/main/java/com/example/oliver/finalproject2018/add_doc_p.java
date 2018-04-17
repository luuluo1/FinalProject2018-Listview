package com.example.oliver.finalproject2018;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class add_doc_p extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc_p);

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.f_holder,new doc_p_Frag());
        ft.commit();
    }
}
