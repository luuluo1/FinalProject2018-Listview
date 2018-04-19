package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class ShowQuest extends Activity {
    protected static final String ACTIVITY_NAME = "ShowQuest";
    Fragment df;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quest);

        Bundle infoToPass = getIntent().getExtras();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(infoToPass.getString("type").equals("isMulti")) {
             df = new MultiFragment();
        }
        else if(infoToPass.getString("type").equals("isNum")) {
             df = new NumFragment();
        }
        else {
             df = new TFFragment();
        }

        df.setArguments(infoToPass);
        ft.addToBackStack(null); //only undo FT on back button
        ft.replace(R.id.empty_fl, df);
        ft.commit();
        Log.i(ACTIVITY_NAME, "is finished");
    }
}
