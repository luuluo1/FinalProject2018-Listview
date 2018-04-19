package com.example.oliver.finalproject2018;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class TFFragment extends Fragment {
    Bundle getInfo;
    TextView TFQust;
    RadioButton rbtA;
    RadioButton rbtB;
    Button del_b;
    Button update_b;
    Boolean isTablet;
    Long id;
    String quest;
    String ans;

    private SQLiteDatabase writableDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInfo = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.fragment_tf, null);

        TFQust = (TextView) gui.findViewById(R.id.TFQust);
        rbtA = (RadioButton) gui.findViewById(R.id.rbtA);
        rbtB = (RadioButton) gui.findViewById(R.id.rbtB);

        del_b = (Button) gui.findViewById(R.id.del_b);
        update_b = (Button) gui.findViewById(R.id.update_b);

        isTablet = getInfo.getBoolean("isTablet");
        id = getInfo.getLong("id");
        quest = getInfo.getString("TFCon");
        ans = getInfo.getString("TFAns");

        TFQust.setText(quest);
        if(ans.equals("true")){
            rbtA.setChecked(true);
            rbtB.setEnabled(true);
        }else{
            rbtB.setChecked(true);
            rbtA.setEnabled(true);
        }

        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet) {
                    DatabaseHelper mydb = new DatabaseHelper(getActivity());
                    writableDB = mydb.getWritableDatabase();
                    writableDB.delete(mydb.TF_TABLE, mydb.TF_ID + "=" + id, null);
                    getActivity().finish();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    getActivity().setResult(13, intent);
                    getActivity().finish();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditeTFQuest.class);
                intent.putExtra("id", id);
                intent.putExtra("quest", quest);
                intent.putExtra("ans", ans);

                startActivity(intent);
            }
        });

        return gui;
    }
}
