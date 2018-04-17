package com.example.oliver.finalproject2018;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oliver.finalproject2018.dummy.Doc_patient;
import com.example.oliver.finalproject2018.dummy.Opt_patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

import java.util.ArrayList;

public class pif_doc_patientlist extends AppCompatActivity {



    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<String> records;
    private PatientDatabaseHelper pb;
    private Doc_patient doc_patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pif_doc_patientlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView opt_list=findViewById(R.id.list_doc_patients);
        pb=new PatientDatabaseHelper(getApplicationContext());
        db=pb.getWritableDatabase();

        String query="Select * From "+PatientDatabaseHelper.TABLE_DOC_PATIENT+";";
        cursor=db.rawQuery(query,null);
        Doc_Pat_adapter pa= new Doc_Pat_adapter(getApplicationContext(),cursor);

        opt_list.setAdapter(pa);
        pa.changeCursor(cursor);


        ListView Pat_list=findViewById(R.id.list_doc_patients);

        ImageButton to_add=findViewById(R.id.btn_add_doc_patient);
        to_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    // Portrait Mode
                    Intent i1=new Intent(getApplicationContext(),add_doc_p.class);
                    startActivity(i1);

                } else {
                    // Landscape Mode
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.doc_placeholder, new doc_p_Frag());

                    ft.commit();
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public ArrayList<Doc_patient> getAllPatients(){
         ArrayList<Doc_patient> doc_patients=null;
        db=pb.getWritableDatabase();
        cursor=db.rawQuery("Select * From "+ PatientDatabaseHelper.TABLE_DOC_PATIENT,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String s_name= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DOC_NAME));
            int id =cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));
            String s_birth= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH));
            String s_Address= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ADDRESS));
            String s_hCard=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_CARD));
            String s_des=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DESCRIPTION));
            String s_phone=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_PHONE));


            String s_pre=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_SURGERIES));
            String s_allergy=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ALERGIES));

            doc_patient.setPreviousSurgery(s_pre);
            doc_patient.setAllergies(s_allergy);

            doc_patient.setAddress(s_Address);
            doc_patient.setBirthday(s_birth);
            doc_patient.setName(s_name);
            doc_patient.setDescription(s_des);
            doc_patient.setHealthCard(s_hCard);
            doc_patient.setPhoneNumber(s_phone);

            doc_patient.setID(id);

            doc_patients.add(doc_patient);


        }
        return doc_patients;

    }
    public class Doc_Pat_adapter extends CursorAdapter {

        public Doc_Pat_adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_opt_view_tiem,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView pat_name = view.findViewById(R.id.Item_view_name);
            String name = cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_NAME));

            TextView pat_ID = view.findViewById(R.id.Item_view_ID);
           final int id = cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));

            pat_name.setText(name);
            pat_ID.setText(String.valueOf(id));

            Button update_btn=view.findViewById(R.id.Update_buttton);
            Button delete_btn=view.findViewById(R.id.Delete_button);

            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(),add_update_doc.class);
                    i1.putExtra("EXTRA_SESSION_ID", id);

                startActivity(i1);

                }  }
            );
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.delete(PatientDatabaseHelper.TABLE_DOC_PATIENT,PatientDatabaseHelper.COLUMN_DOC_PATIENT_ID+"="+id,null);
                    notifyDataSetChanged();
                    recreate();
                }
            });
        }


    }}
