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

        TextView t1=findViewById(R.id.update_tttt);
        String a=String.valueOf(id);
        t1.setText(a);

        Bundle b1=new Bundle();
        b1.putString("sss1",a);

        update_opt uo=new update_opt();


        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        uo.setPatientId(id);
        ft.replace(R.id.a_opt_holder,uo);
        ft.commit();
    }
}
