package com.example.oliver.finalproject2018;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oliver.finalproject2018.R;
import com.example.oliver.finalproject2018.dummy.Patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;



public class update_opt extends Fragment {
    int id;

    public void setPatientId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return id;
    }

    EditText opt_name;
    EditText opt_address;
    EditText opt_birth;
    EditText opt_phone;
    EditText opt_hCard;
    EditText opt_description;
    EditText opt_glass;
    EditText opt_store;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_opt,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    //    super.onViewCreated(view, savedInstanceState);
        Button sub_btn=getView().findViewById(R.id.opt_submit);

        PatientDatabaseHelper pd=new PatientDatabaseHelper(getContext());
        SQLiteDatabase dbS=pd.getWritableDatabase();

        TextView p_id=getActivity().findViewById(R.id.opt_pat_id);
        p_id.setText("          Patient ID is : "+String.valueOf(id));



        opt_name=getActivity().findViewById(R.id.opt_pat_name);
        opt_address=getActivity().findViewById(R.id.opt_pat_address);
        opt_birth=getActivity().findViewById(R.id.opt_pat_birthday);
        opt_hCard=getActivity().findViewById(R.id.opt_pat_health_number);
        opt_glass=getActivity().findViewById(R.id.opt_pat_glasses_purchase_Date);
        opt_store=getActivity().findViewById(R.id.opt_pat_glasses_store);
        opt_description=getActivity().findViewById(R.id.opt_pat_description);
        opt_phone=getActivity().findViewById(R.id.opt_pat_phone_number);

        Cursor cursor= dbS.rawQuery("SELECT * FROM OPT_PATIENT WHERE _id="+String.valueOf(id),null);

        cursor.moveToFirst();

            opt_name.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_OPT_NAME)));
            opt_address.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ADDRESS)));
            opt_birth.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH)));
            opt_hCard.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH)));
        //    opt_description.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DESCRIPTION)));
            opt_phone.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_PHONE)));
            opt_glass.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_GLASSES_PURCHASE_DATE)));
            opt_store.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_GLASSES_STORE)));

            Button clear_btn=getView().findViewById(R.id.opt_pat_clear);
            clear_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    opt_name.setText("");
                    opt_address.setText("");
                    opt_birth.setText("");
                    opt_hCard.setText("");
                    opt_glass.setText("");
                    opt_hCard.setText("");
                    opt_phone.setText("");
                    opt_store.setText("");

                }
            });



        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                PatientDatabaseHelper pdh=new PatientDatabaseHelper(getContext());
                ContentValues cv=new ContentValues();
                SQLiteDatabase db=pdh.getWritableDatabase();

                cv.put(PatientDatabaseHelper.COLUMN_OPT_NAME,opt_name.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_ADDRESS,opt_address.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_BIRTH,opt_address.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_PHONE,opt_phone.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_DESCRIPTION,opt_description.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_HEALTH_CARD,opt_hCard.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_GLASSES_PURCHASE_DATE,opt_glass.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_GLASSES_STORE,opt_store.getText().toString());

                db.insert(PatientDatabaseHelper.TABLE_OPT_PATIENT,null,cv);
                Intent i1=new Intent(getContext(),pif_opt_patientList.class);
                startActivity(i1);
            }
        });

    }
}