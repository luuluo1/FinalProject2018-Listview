package com.example.oliver.finalproject2018;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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

import com.example.oliver.finalproject2018.dummy.Opt_patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class pif_opt_patientList extends AppCompatActivity {


    private ArrayList<Opt_patient> ops;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<String> records;
    private PatientDatabaseHelper pb;
    private Opt_patient opt_patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pif_opt_patient_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
         final   int id = cursor.getInt(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID));

            pat_name.setText(name);
            pat_ID.setText(String.valueOf(id));


            Button update_btn=view.findViewById(R.id.Update_buttton);
            Button delete_btn=view.findViewById(R.id.Delete_button);

            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent i1=new Intent(getApplicationContext(), add_update_opt.class);
                i1.putExtra("EXTRA_SESSION_ID", id);
                startActivity(i1);
                }
            }
            );
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.delete(PatientDatabaseHelper.TABLE_OPT_PATIENT,PatientDatabaseHelper.COLUMN_OPT_PATIENT_ID+"="+id,null);
                    notifyDataSetChanged();
                    recreate();
                }
            });
        }
    }
}
