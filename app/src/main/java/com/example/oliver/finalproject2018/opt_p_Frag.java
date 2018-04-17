package com.example.oliver.finalproject2018;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.oliver.finalproject2018.R;
import com.example.oliver.finalproject2018.dummy.Patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;


public class opt_p_Frag extends Fragment {

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
        return inflater.inflate(R.layout.fragment_opt_patient,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button sub_btn=getView().findViewById(R.id.opt_submit);



      sub_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              opt_name=getActivity().findViewById(R.id.opt_pat_name);
              opt_address=getView().findViewById(R.id.opt_pat_address);
              opt_birth=getView().findViewById(R.id.opt_pat_birthday);
              opt_hCard=getView().findViewById(R.id.opt_pat_health_number);
              opt_glass=getView().findViewById(R.id.opt_pat_glasses_purchase_Date);
              opt_store=getView().findViewById(R.id.opt_pat_glasses_store);
              opt_description=getView().findViewById(R.id.opt_pat_description);
              opt_phone=getView().findViewById(R.id.opt_pat_phone_number);



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
