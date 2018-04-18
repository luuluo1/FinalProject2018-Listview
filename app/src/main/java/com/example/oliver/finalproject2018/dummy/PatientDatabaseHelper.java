package com.example.oliver.finalproject2018.dummy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.security.Key;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by oliver on 4/11/2018.
 */

public class PatientDatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Patient.db";
    private static final int DATABASE_VERSION = 8;

        public static final String TABLE_DOC_PATIENT="DOC_PATIENT";
        public static final String TABLE_DEN_PATIENT="DEN_PATIENT";
        public static final String TABLE_OPT_PATIENT="OPT_PATIENT";

        public static final String COLUMN_OPT_PATIENT_ID="_id";
        public static final String COLUMN_DOC_PATIENT_ID="_id";
        public static final String COLUMN_DEN_PATIENT_ID="_id";

        public static final String COLUMN_OPT_NAME="PATIENT_NAME";
        public static final String COLUMN_DOC_NAME="PATIENT_NAME";
        public static final String COLUMN_DEN_NAME="PATIENT_NAME";

        public static final String COLUMN_ADDRESS="PATIENTADDRESS";
        public static final String COLUMN_BIRTH ="BIRTHDAY";
        public static final String COLUMN_PHONE="PHONE";
        public static final String COLUMN_HEALTH_CARD="HEALTHCARD";
        public static final String COLUMN_DESCRIPTION =" PATIENTDESCRIPTION";

        public static final String COLUMN_BRACES ="HAVEBRACES";
        public static final String COLUMN_HEALTH_BENFIT="HEALTHBENIFIT";

        public static final String COLUMN_SURGERIES ="PREVIOUSSURGERIES";
        public static final String COLUMN_ALERGIES ="ALERGIES";

        public static final String COLUMN_GLASSES_PURCHASE_DATE="CLASSPURCHASEDATE";
        public static final String COLUMN_GLASSES_STORE="GLASSESSTORE";

        public static final String CREATE_DEN_TABLE= "CREATE TABLE " + TABLE_DEN_PATIENT + "("
                + COLUMN_DEN_PATIENT_ID + " INTEGER PRIMARY KEY , "
                + COLUMN_DEN_NAME + " TEXT , "
                + COLUMN_ADDRESS + " TEXT , "
                + COLUMN_BIRTH + " DATE , "
                + COLUMN_PHONE + " TEXT , "
                + COLUMN_HEALTH_CARD + " TEXT , "
                + COLUMN_DESCRIPTION + " TEXT , "
                + COLUMN_HEALTH_BENFIT + " TEXT , "
                + COLUMN_BRACES + " TEXT  );";

        public static final String CREATE_DOC_TABLE="CREATE TABLE " + TABLE_DOC_PATIENT + "("
                + COLUMN_DOC_PATIENT_ID + " INTEGER PRIMARY KEY , "
                + COLUMN_DOC_NAME + " TEXT , "
                + COLUMN_ADDRESS + " TEXT , "
                + COLUMN_BIRTH + " DATE , "
                + COLUMN_PHONE + " TEXT , "
                + COLUMN_HEALTH_CARD + " TEXT , "
                + COLUMN_DESCRIPTION + " TEXT , "
                + COLUMN_SURGERIES + " TEXT , "
                + COLUMN_ALERGIES + " TEXT  );";

        public static final String CREATE_OPT_TABLE="CREATE TABLE " + TABLE_OPT_PATIENT + "("
            + COLUMN_OPT_PATIENT_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_OPT_NAME + " TEXT , "
            + COLUMN_ADDRESS + " TEXT , "
            + COLUMN_BIRTH + " DATE , "
            + COLUMN_PHONE + " TEXT , "
            + COLUMN_HEALTH_CARD + " TEXT , "
            + COLUMN_DESCRIPTION + " TEXT , "
            + COLUMN_GLASSES_PURCHASE_DATE + " DATE , "
            + COLUMN_GLASSES_STORE + " TEXT  );";


        public PatientDatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public PatientDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
        public void onCreate(SQLiteDatabase db) {
           db.execSQL(CREATE_DEN_TABLE);
            Log.i("sql", "create dental table");
           db.execSQL(CREATE_DOC_TABLE);
            Log.i("sql", "create doctor table");
            db.execSQL(CREATE_OPT_TABLE);
            Log.i("sql", "create opt table");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_OPT_PATIENT);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_DEN_PATIENT);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_DOC_PATIENT);

            onCreate(db);
            Log.i("update", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
        }
    public int maxAge(SQLiteDatabase db){
       //     Cursor c=db.rawQuery();
        return 0;
    }

    public int avgAge(SQLiteDatabase db){
    //    Cursor ;
return 0;
    }
    public int minAge(SQLiteDatabase db){
       // Cursor c=
    return 0;
    }
    }

