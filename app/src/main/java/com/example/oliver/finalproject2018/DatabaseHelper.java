package com.example.oliver.finalproject2018;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by minyiyang on 2018-04-09.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Mutil quest table
    public static final String DATABASE_NAME = "Quest.db";
    public static final int VERSION_NUM = 3;
    public static final String Mtil_TABLE = "Mtil_T";
    public static final String Mtil_ID = "Mtil_ID";
    public static final String Mtil_QUEST = "Mtil_QUEST";
    public static final String Mtil_ANSA = "Mtil_ANSA";
    public static final String Mtil_ANSB = "Mtil_ANSB";
    public static final String Mtil_ANSC = "Mtil_ANSC";
    public static final String Mtil_ANSD = "Mtil_ANSD";
    public static final String Mtil_CORRT = "Mtil_CORRT";
    //Numic quest table
    public static final String Num_TABLE = "Num_T";
    public static final String Num_ID = "Num_ID";
    public static final String Num_QUEST = "Num_QUEST";
    public static final String Num_ANS = "Num_ANS";
    public static final String Num_ACC = "Num_ACC";

    //t/f quest table
    public static final String TF_TABLE = "TF_T";
    public static final String TF_ID = "TF_ID";
    public static final String TF_QUEST = "TF_QUEST";
    public static final String TF_ANS = "TF_ANS";

    public DatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + Mtil_TABLE + " (" + Mtil_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Mtil_QUEST + " TEXT," +
                    Mtil_ANSA + " TEXT," +
                    Mtil_ANSB + " TEXT," +
                    Mtil_ANSC + " TEXT," +
                    Mtil_ANSD + " TEXT," +
                    Mtil_CORRT + " TEXT)");

            db.execSQL("CREATE TABLE " + Num_TABLE + " (" + Num_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Num_QUEST + " TEXT," +
                    Num_ANS + " TEXT,"+ Num_ACC + " TEXT)");

            db.execSQL("CREATE TABLE " + TF_TABLE + " (" + TF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TF_QUEST + " TEXT," +
                    TF_ANS + " TEXT)");
        }
        catch (SQLException e) {
            Log.e("QuizPoolDatabaseHelper", e.getMessage());
        }
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Mtil_TABLE+ ";");
        db.execSQL("DROP TABLE IF EXISTS " + Num_TABLE+ ";");
        db.execSQL("DROP TABLE IF EXISTS " + TF_TABLE+ ";");
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Mtil_TABLE+ ";");
        db.execSQL("DROP TABLE IF EXISTS " + Num_TABLE+ ";");
        db.execSQL("DROP TABLE IF EXISTS " + TF_TABLE+ ";");
        onCreate(db);
        Log.i("QuizPoolDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion= " + newVersion);
    }
}
