package com.example.oliver.finalproject2018;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.oliver.finalproject2018.R;
import com.example.oliver.finalproject2018.dummy.Patient;
import com.example.oliver.finalproject2018.dummy.PatientDatabaseHelper;

/**
 * Created by oliver on 4/11/2018.
 */

public class den_p_Frag extends Fragment {

    public static final String TAG="A";

     EditText den_name;
     EditText den_address;
     EditText den_birth;
     EditText den_phone;
     EditText den_hCard;
     EditText den_descri;

    RadioButton br_yes;
    RadioButton br_no;
     RadioButton hl_yes;
     RadioButton hl_no;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_den_patient,container,false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


        super.onViewCreated(view, savedInstanceState);

        Button Sub_btn=getView().findViewById(R.id.den_submit);

        Sub_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                den_name=getActivity().findViewById(R.id.den_pat_name);
                den_address=getView().findViewById(R.id.den_pat_address);
                den_descri=getActivity().findViewById(R.id.den_pat_description);
                den_birth=getView().findViewById(R.id.den_pat_birthday);
                den_hCard=getView().findViewById(R.id.den_pat_health_number);
                den_phone=getView().findViewById(R.id.den_pat_phone_number);

                br_yes=getView().findViewById(R.id.den_yes_braces);
                br_no=getView().findViewById(R.id.den_no_braces);
                hl_yes=getView().findViewById(R.id.den_yes_hbenefit);
                hl_no=getView().findViewById(R.id.den_no_hbenefit);



                PatientDatabaseHelper pb=new PatientDatabaseHelper(getContext());
                SQLiteDatabase db=pb.getWritableDatabase();
                ContentValues cv=new ContentValues();

                cv.put(PatientDatabaseHelper.COLUMN_DEN_NAME,den_name.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_ADDRESS,den_address.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_BIRTH,den_birth.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_DESCRIPTION,den_descri.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_HEALTH_CARD,den_hCard.getText().toString());
                cv.put(PatientDatabaseHelper.COLUMN_PHONE,den_phone.getText().toString());

                if(br_yes.isChecked())
                    cv.put(PatientDatabaseHelper.COLUMN_BRACES,"Yes");
                else cv.put(PatientDatabaseHelper.COLUMN_BRACES,"No");

                if(hl_yes.isChecked())
                    cv.put(PatientDatabaseHelper.COLUMN_HEALTH_BENFIT,"Yes");
                 else cv.put(PatientDatabaseHelper.COLUMN_HEALTH_BENFIT,"No");


                db.insert(PatientDatabaseHelper.TABLE_DEN_PATIENT,null,cv);

                Intent i1=new Intent(getContext(),pif_den_patientlist.class);
                startActivity(i1);
            }
        });
    }
}
