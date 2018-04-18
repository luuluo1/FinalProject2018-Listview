package com.example.oliver.finalproject2018;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oliver.finalproject2018.dummy.Doc_patientDao;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;



public class update_doc extends Fragment {
    public static final String TAG="AddDocPatient";
    EditText dTxtName;
    EditText dTxtAddress;
    EditText dTxtBirth;
    EditText dTxtPhone;
    EditText dTxtHCard;
    EditText dDescr;
    EditText dPre;
    EditText dAllergy;
    int id;
    private Doc_patientDao doc_patientDao;

    public void setdocId(int id) {
        this.id = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_doc,container,false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

       // super.onViewCreated(view, savedInstanceState);
        Button sub_btn=getView().findViewById(R.id.u_doc_submit);


        dTxtName=getActivity().findViewById(R.id.u_pat_name);

        dDescr=getActivity().findViewById(R.id.u_doc_pat_description);
        dAllergy=getActivity().findViewById(R.id.u_doc_pat_allergies);
        dTxtAddress=getActivity().findViewById(R.id.u_doc_pat_address);
        dTxtBirth=getActivity().findViewById(R.id.u_doc_pat_birthday);
        dPre=getActivity().findViewById(R.id.u_doc_pat_previous_surg);
        dTxtPhone=getActivity().findViewById(R.id.u_doc_pat_phone_number);
        dTxtHCard=getActivity().findViewById(R.id.u_doc_pat_health_number);

        PatientDatabaseHelper pd1=new PatientDatabaseHelper(getContext());
        SQLiteDatabase db1=pd1.getWritableDatabase();


        Cursor cursor=db1.rawQuery("SELECT * FROM DOC_PATIENT WHERE _id = "+String.valueOf(id),null);

        cursor.moveToFirst();
                dTxtName.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DOC_NAME)));
                dDescr.setText(cursor.getString(cursor.getColumnIndex("PATIENTDESCRIPTION")));
                dPre.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_SURGERIES)));
                dTxtPhone.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_PHONE)));
                dTxtBirth.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH)));
                dTxtHCard.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_CARD)));
                dAllergy.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ALERGIES)));
                dTxtAddress.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ADDRESS)));

        Button b1=getView().findViewById(R.id.U_doc_pat_clear);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dTxtName.setText("");
                dDescr.setText("");
                dPre.setText("");
                dTxtPhone.setText("");
                dTxtBirth.setText("");
                dTxtHCard.setText("");
                dAllergy.setText("");
                dTxtAddress.setText("");

            }
        });



        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if(validationSuccess()) {
    PatientDatabaseHelper pd = new PatientDatabaseHelper(getContext());
    SQLiteDatabase db = pd.getWritableDatabase();

    ContentValues cv = new ContentValues();

    cv.put(PatientDatabaseHelper.COLUMN_DOC_NAME, dTxtName.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_PHONE, dTxtPhone.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_DESCRIPTION, dDescr.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_HEALTH_CARD, dTxtHCard.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_ALERGIES, dAllergy.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_SURGERIES, dPre.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_BIRTH, dTxtBirth.getText().toString());
    cv.put(PatientDatabaseHelper.COLUMN_ADDRESS, dTxtAddress.getText().toString());

    db.insert(PatientDatabaseHelper.TABLE_DOC_PATIENT, null, cv);
    Toast.makeText(getActivity(),"Submit Success!",Toast.LENGTH_SHORT).show();

    Intent i1 = new Intent(getContext(), pif_doc_patientlist.class);
    startActivity(i1);
}
else{}
            }
        });
    }private Boolean validationSuccess()
    {
        if(dTxtName.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dTxtAddress.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter address",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dTxtPhone.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Phone",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dTxtHCard.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Health Card Number",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dAllergy.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Allergies",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dDescr.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Description",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(dPre.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Previous Surgery",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(dTxtBirth.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Birth Date",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}