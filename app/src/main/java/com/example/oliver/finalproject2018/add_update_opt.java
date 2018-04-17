package com.example.oliver.finalproject2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

public class add_update_opt extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_opt);

        int id=getIntent().getExtras().getInt("EXTRA_SESSION_ID");
        String a=String.valueOf(id);
        Bundle b1=new Bundle();
        b1.putString("sss1",a);

        update_opt uo=new update_opt();


        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        uo.setPatientId(id);
        ft.replace(R.id.pt_u_holder,uo);
        ft.commit();
    }
}
