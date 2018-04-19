package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NumFragment extends Fragment {
    Bundle getInfo;
    TextView NumQust;
    TextView Numans;
    TextView Numacc;
    Button del_b;
    Button update_b;
    Boolean isTablet;
    Long id;
    String quest;
    String ans;
    String acc;

    private SQLiteDatabase writableDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInfo = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.fragment_num, null);

        NumQust = (TextView) gui.findViewById(R.id.NumQust);
        Numans = (TextView) gui.findViewById(R.id.Numans);
        Numacc = (TextView) gui.findViewById(R.id.Numacc);

        del_b = (Button) gui.findViewById(R.id.del_b);
        update_b = (Button) gui.findViewById(R.id.update_b);

        isTablet = getInfo.getBoolean("isTablet");
        id = getInfo.getLong("id");
        quest = getInfo.getString("NumCon");
        ans = getInfo.getString("NumAns");
        acc = getInfo.getString("NumAcc");

        NumQust.setText(quest);
        Numans.setText(ans);
        Numacc.setText(acc);

        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet) {
                    DatabaseHelper mydb = new DatabaseHelper(getActivity());
                    writableDB = mydb.getWritableDatabase();
                    writableDB.delete(mydb.Num_TABLE, mydb.Num_ID + "=" + id, null);
                    getActivity().finish();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    getActivity().setResult(12, intent);
                    getActivity().finish();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditeNumQuest.class);
                intent.putExtra("id", id);
                intent.putExtra("quest", quest);
                intent.putExtra("ans", ans);
                intent.putExtra("acc", acc);
                startActivity(intent);
            }
        });

        return gui;
    }
}
