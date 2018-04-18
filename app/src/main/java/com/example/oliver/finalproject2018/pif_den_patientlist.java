package com.example.oliver.finalproject2018;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oliver.finalproject2018.dummy.Den_patient;
import com.example.oliver.finalproject2018.dummy.Doc_patient;
import com.example.oliver.finalproject2018.dummy.Opt_patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

import java.util.ArrayList;

public class pif_den_patientlist extends AppCompatActivity {

    Snackbar snackbar;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<String> records;
    private PatientDatabaseHelper pb;
    private Den_patient den_patient;
    private boolean isTablet = false;
        Toolbar t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pif_den_patientlist);
        t1=findViewById(R.id.Den_toolbar);
        setSupportActionBar(t1);

        isTablet = (findViewById(R.id.den_placeholder) != null);

        ListView opt_list=findViewById(R.id.list_den_patients);
        pb=new PatientDatabaseHelper(getApplicationContext());
        db=pb.getWritableDatabase();

        String query="Select * From "+PatientDatabaseHelper.TABLE_DEN_PATIENT+";";
        cursor=db.rawQuery(query,null);
        Den_Pat_adapter pa= new Den_Pat_adapter(getApplicationContext(),cursor);

        opt_list.setAdapter(pa);
        pa.changeCursor(cursor);

        ImageButton add_den_btn=findViewById(R.id.btn_add_den_patient);

        add_den_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTablet)
                {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.den_placeholder, new den_p_Frag());
                    ft.commit();
                }  else{

                    Intent i1=new Intent(getApplicationContext(),add_den_p.class);
                    startActivity(i1);

                }
            }
        });


    }
    public ArrayList<Den_patient> getAllPatients(){
         ArrayList<Den_patient> den_patients=null;
        db=pb.getWritableDatabase();
        cursor=db.rawQuery("Select * From "+ PatientDatabaseHelper.TABLE_DEN_PATIENT,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            String s_name= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DEN_NAME));
            int id =cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));
            String s_birth= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH));
            String s_Address= cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ADDRESS));
            String s_hCard=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_CARD));
            String s_des=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DESCRIPTION));
            String s_phone=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_PHONE));


            String s_brace=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BRACES));
            String s_ben=cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_BENFIT));

            den_patient.setBenifits(s_ben);
            den_patient.setHadBraces(s_brace);

            den_patient.setAddress(s_Address);
            den_patient.setBirthday(s_birth);
            den_patient.setName(s_name);
            den_patient.setDescription(s_des);
            den_patient.setHealthCard(s_hCard);
            den_patient.setPhoneNumber(s_phone);

            den_patient.setID(id);

            den_patients.add(den_patient);


        }
        return den_patients;

    }
    public class Den_Pat_adapter extends CursorAdapter {

        public Den_Pat_adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_opt_view_tiem,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView pat_name = view.findViewById(R.id.Item_view_name);
          final  String name = cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_NAME));

            TextView pat_ID = view.findViewById(R.id.Item_view_ID);
           final int _id = cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));

            pat_name.setText(name);
            pat_ID.setText(String.valueOf(_id));
            Button update_btn=view.findViewById(R.id.Update_buttton);
            Button delete_btn=view.findViewById(R.id.Delete_button);

            update_btn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {
                    if(isTablet)
                    {      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        update_den ud=new update_den(); ud.setdenId(_id);
                        ft.replace(R.id.den_placeholder, ud);
                        ft.commit();



                    } else {

                        Intent i1=new Intent(getApplicationContext(),add_update_den.class);
                        i1.putExtra("EXTRA_SESSION_ID", _id);
                        startActivity(i1);
                    }



                }
            });
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(pif_den_patientlist.this);
                    String s1="Delete "+name +" 's Record?";
                    builder.setMessage(s1)
                            .setPositiveButton(R.string.den_yes_healthb, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    db.delete(PatientDatabaseHelper.TABLE_DEN_PATIENT,PatientDatabaseHelper.COLUMN_DEN_PATIENT_ID+"="+_id,null);
                                    notifyDataSetChanged();
                                    recreate();
                                }
                            })
                            .setNegativeButton(R.string.den_no_healthb, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_den_patient,menu);
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
                Intent i1=new Intent(getApplicationContext(),pif_start.class);
                startActivity(i1);
                break;
            case R.id.PIF_SAMPLEdata:
                Intent i2=new Intent(getApplicationContext(),LoadSamplePatient.class);
                startActivity(i2);
                break;
            case R.id.d_go_to_Doc_page:
              Intent  i3=new Intent(getApplicationContext(),pif_doc_patientlist.class);
                startActivity(i3);
                break;
            case R.id.d_go_to_Opt_page:
               Intent i4=new Intent(getApplicationContext(),pif_opt_patientList.class);
                startActivity(i4);
                break;

            case R.id.PIF_den_stat:
                PatientDatabaseHelper pdSS=new PatientDatabaseHelper(getApplicationContext());
                SQLiteDatabase sdt=pdSS.getReadableDatabase();
                int a=pdSS.denmaxAge(sdt);
                int b=pdSS.denminAge(sdt);
                int c=pdSS.denavgAge(sdt);
                snackbar.make(findViewById(android.R.id.content), "The Max age is :"+ a+"; The Min age is : "+ b+"; The Avg age is: "+c , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d("Toolbar","Option camera selected");
                break;
        }return true;
    }
}
