package com.example.oliver.finalproject2018;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PIF_StartActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private ArrayList<String> s1 ;

    private String s_docOffice = "Doctor's Office";
    private String s_dentist ="Dentist";
    private String s_optometrist ="Optometrist";

    Button den_btn;
    Button doc_btn;
    Button opt_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pif__start);

     den_btn=findViewById(R.id.dental_pat_btn);
        doc_btn=findViewById(R.id.doctor_pat_btn);
         opt_btn=findViewById(R.id.optical_pat_btn);

        den_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),pif_den_patientlist.class);
                startActivity(i1);
            }
        });

        opt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),pif_opt_patientList.class);
                startActivity(i1);
            }
        });
        doc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),pif_doc_patientlist.class);
                startActivity(i1);
            }
        });
    }

}
