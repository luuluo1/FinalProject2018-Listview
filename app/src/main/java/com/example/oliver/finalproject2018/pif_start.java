package com.example.oliver.finalproject2018;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class pif_start extends AppCompatActivity {
    private TextView mTextMessage;
Snackbar snackbar;
    private ArrayList<String> s1 ;

    private String s_docOffice = "Doctor's Office";
    private String s_dentist ="Dentist";
    private String s_optometrist ="Optometrist";
    Toolbar start_toolbar_pif;
    Button den_btn;
    Button doc_btn;
    Button opt_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pif_start);
        den_btn=findViewById(R.id.dental_pat_btn);
        doc_btn=findViewById(R.id.doctor_pat_btn);
        opt_btn=findViewById(R.id.optical_pat_btn);

        start_toolbar_pif=findViewById(R.id.patient_toolbar_start);
        setSupportActionBar(start_toolbar_pif);
        getSupportActionBar().setTitle(R.string.pif_start);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.s_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello! Welcome to my program!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_patient,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.PIF_about:

                snackbar.make(findViewById(android.R.id.content), "Patient Intake Application By Yang Luo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d("Toolbar","Option camera selected");
                break;
            case R.id.PIF_help:
                Toast toast = Toast.makeText(getApplicationContext(), "This Version 1.0 for patient intake system. 2018", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.Back_to_home_page:



                break;
            case R.id.PIF_SAMPLEdata:
                Intent i1=new Intent(getApplicationContext(),LoadSamplePatient.class);
                startActivity(i1);


        }return true;
    }
}

