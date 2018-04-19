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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

public class update_den extends Fragment {

    int id;
    public static final String TAG="A";

    EditText den_name;
    EditText den_address;
    EditText den_birth;
    EditText den_phone;
    EditText den_hCard;
    EditText den_descri;

    EditText den_hstat;
    EditText den_bstat;
Button go_back;
    public void setdenId(int id) {
        this.id = id;
    }


    public int getdenId() {

        return id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_den,container,false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {


        den_name=getActivity().findViewById(R.id.u_den_pat_name);
        den_address=getView().findViewById(R.id.u_den_pat_address);
        den_descri=getView().findViewById(R.id.u_den_pat_description);
        den_birth=getView().findViewById(R.id.u_den_pat_birthday);
        den_hCard=getView().findViewById(R.id.u_den_pat_health_number);
        den_phone=getView().findViewById(R.id.u_den_pat_phone_number);

       den_hstat=getView().findViewById(R.id.u_den_pat_HStat);
       den_bstat=getView().findViewById(R.id.u_den_pat_bStat);
go_back=getView().findViewById(R.id.U_den_pat_goback);
go_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i1=new Intent(getContext(),pif_den_patientlist.class);
        startActivity(i1);

    }
});
        PatientDatabaseHelper pb1=new PatientDatabaseHelper(getContext());
        SQLiteDatabase db1=pb1.getWritableDatabase();

        Cursor cursor=db1.rawQuery("SELECT "+PatientDatabaseHelper.COLUMN_PHONE+","+PatientDatabaseHelper.COLUMN_DESCRIPTION+","+PatientDatabaseHelper.COLUMN_HEALTH_CARD+","+PatientDatabaseHelper.COLUMN_BRACES+","+PatientDatabaseHelper.COLUMN_HEALTH_BENFIT+","+PatientDatabaseHelper.COLUMN_BIRTH+","+PatientDatabaseHelper.COLUMN_ADDRESS+","+PatientDatabaseHelper.COLUMN_DEN_NAME+" FROM DEN_PATIENT WHERE _id = "+String.valueOf(id),null);

        cursor.moveToFirst();
            den_name.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_DEN_NAME)));
            den_address.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_ADDRESS)));
            den_birth.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_BIRTH)));

            den_hCard.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_CARD)));
            den_phone.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_PHONE)));
            den_bstat.setText(cursor.getString(cursor.getColumnIndex("HAVEBRACES")));
            den_hstat.setText(cursor.getString(cursor.getColumnIndex(PatientDatabaseHelper.COLUMN_HEALTH_BENFIT)));
            den_descri.setText(cursor.getString(cursor.getColumnIndex("PATIENTDESCRIPTION")));

            Button den_clear_btn=getView().findViewById(R.id.den_pat_clear);
                    den_clear_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            den_name.setText("");
                            den_address.setText("");
                            den_birth.setText("");
                            den_descri.setText("");
                            den_hCard.setText("");
                            den_phone.setText("");
                            den_bstat.setText("");
                            den_bstat.setText("");
                        }
                    });



        Button Sub_btn=getView().findViewById(R.id.den_submit);

        Sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(validationSuccess()){
                        PatientDatabaseHelper pb=new PatientDatabaseHelper(getContext());
                        SQLiteDatabase db=pb.getWritableDatabase();
                        ContentValues cv=new ContentValues();

                        cv.put(PatientDatabaseHelper.COLUMN_DEN_NAME,den_name.getText().toString());
                        cv.put(PatientDatabaseHelper.COLUMN_ADDRESS,den_address.getText().toString());
                        cv.put(PatientDatabaseHelper.COLUMN_BIRTH,den_birth.getText().toString());
                        cv.put(PatientDatabaseHelper.COLUMN_DESCRIPTION,den_descri.getText().toString());
                        cv.put(PatientDatabaseHelper.COLUMN_HEALTH_CARD,den_hCard.getText().toString());
                        cv.put(PatientDatabaseHelper.COLUMN_PHONE,den_phone.getText().toString());

                        cv.put(PatientDatabaseHelper.COLUMN_HEALTH_BENFIT,den_hstat.getText().toString());
                        cv.put("HAVEBRACES",den_bstat.getText().toString());

    db.update(PatientDatabaseHelper.TABLE_DEN_PATIENT,cv, "_id = ?",new String[]{String.valueOf(id)});
      Toast.makeText(getActivity(),"Submit Success!",Toast.LENGTH_SHORT).show();

                        Intent i1=new Intent(getContext(),pif_den_patientlist.class);
                        startActivity(i1);} else{}
            }
        });
    }
    private Boolean validationSuccess()
    {
        if(den_name.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(den_address.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter address",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(den_phone.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Phone",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(den_hCard.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Health Card Number",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(den_descri.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Description",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(den_bstat.getText().toString().equalsIgnoreCase(""))
    {
        Toast.makeText(getActivity(),"Please enter Yes or No for Braces",Toast.LENGTH_SHORT).show();
        return false;
    }
        if(den_hstat.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Yes or No for Health Benefit",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(den_birth.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(getActivity(),"Please enter Birth Date",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
