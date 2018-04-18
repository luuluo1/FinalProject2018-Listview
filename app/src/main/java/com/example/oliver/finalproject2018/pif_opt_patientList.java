package com.example.oliver.finalproject2018;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oliver.finalproject2018.dummy.Opt_patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class pif_opt_patientList extends AppCompatActivity {

Snackbar snackbar;
    private ArrayList<Opt_patient> ops;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<String> records;
    private PatientDatabaseHelper pb;
    private Opt_patient opt_patient;
    Toolbar t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pif_opt_patient_list);

        t1=findViewById(R.id.opt_patient_toolbar);
        setSupportActionBar(t1);

        ListView opt_list=findViewById(R.id.list_opt_patients);
        pb=new PatientDatabaseHelper(getApplicationContext());
        db=pb.getWritableDatabase();

        String query="Select * From "+PatientDatabaseHelper.TABLE_OPT_PATIENT+";";
        cursor=db.rawQuery(query,null);
        Opt_Pat_adapter pa= new Opt_Pat_adapter(getApplicationContext(),cursor);

        opt_list.setAdapter(pa);
        pa.changeCursor(cursor);
        pa.notifyDataSetChanged();




        ImageButton ib_add_opt=findViewById(R.id.btn_add_opt);

        ib_add_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getApplication().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    // Portrait Mode
                    Intent i1=new Intent(getApplicationContext(),add_opt_p.class);
                    startActivity(i1);

                } else {
                    // Landscape Mode
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.opt_placeholder, new opt_p_Frag());
                    ft.commit();
                }
            }
        });



}

    public ArrayList<Opt_patient> getAllPatients(){
        opt_patient=new Opt_patient();
        ArrayList<Opt_patient> opt_patients=new ArrayList<>();
        pb=new PatientDatabaseHelper(getApplicationContext());
        db=pb.getWritableDatabase();

        String query="Select * From "+PatientDatabaseHelper.TABLE_OPT_PATIENT+";";
        cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        return null;

    }

    public class Opt_Pat_adapter extends CursorAdapter {

        public Opt_Pat_adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_opt_view_tiem,parent,false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView pat_name = view.findViewById(R.id.Item_view_name);
         final   String name = cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_NAME));

            TextView pat_ID = view.findViewById(R.id.Item_view_ID);
         final   int _id = cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));

            pat_name.setText(name);
            pat_ID.setText(String.valueOf(_id));


            Button update_btn=view.findViewById(R.id.Update_buttton);
            Button delete_btn=view.findViewById(R.id.Delete_button);

            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(), add_update_opt.class);
                i1.putExtra("EXTRA_SESSION_ID", _id);
                startActivity(i1);
                }
            }
            );
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(pif_opt_patientList.this);
                    String s1="Delete "+name +" 's Record?";
                    builder.setMessage(s1)
                            .setPositiveButton(R.string.den_yes_healthb, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    db.delete(PatientDatabaseHelper.TABLE_OPT_PATIENT,PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID+"="+_id,null);
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
        getMenuInflater().inflate(R.menu.menu_opt_patient,menu);
        return super.onCreateOptionsMenu(menu);
    }

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
                 i1=new Intent(getApplicationContext(),LoadSamplePatient.class);
                startActivity(i1);
                break;
            case R.id.op_go_to_den_page:
                i1=new Intent(getApplicationContext(),pif_den_patientlist.class);
                startActivity(i1);
                break;
            case R.id.op_go_to_doc_page:
                i1=new Intent(getApplicationContext(),pif_doc_patientlist.class);
                startActivity(i1);
                break;
        }return true;
    }
}
