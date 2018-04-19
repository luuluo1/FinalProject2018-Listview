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

public class MultiFragment extends Fragment {
    TextView QustCon;
    RadioButton rbA;
    RadioButton rbB;
    RadioButton rbC;
    RadioButton rbD;
    Bundle getInfo;
    TextView textConA;
    TextView textConB;
    TextView textConC;
    TextView textConD;
    Button del_b;
    Button update_b;
    Boolean isTablet;
    Long id;
    String quest;
    String ansA;
    String ansB;
    String ansC;
    String ansD;
    String croans;
    private SQLiteDatabase writableDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInfo = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.fragment_multi, null);

        QustCon = (TextView) gui.findViewById(R.id.QustCon);
        rbA = (RadioButton) gui.findViewById(R.id.rbA);
        rbB = (RadioButton) gui.findViewById(R.id.rbB);
        rbC = (RadioButton) gui.findViewById(R.id.rbC);
        rbD = (RadioButton) gui.findViewById(R.id.rbD);

        textConA = (TextView) gui.findViewById(R.id.textConA);
        textConB = (TextView) gui.findViewById(R.id.textConB);
        textConC = (TextView) gui.findViewById(R.id.textConC);
        textConD = (TextView) gui.findViewById(R.id.textConD);

        del_b = (Button) gui.findViewById(R.id.del_b);
        update_b = (Button) gui.findViewById(R.id.update_b);

        isTablet = getInfo.getBoolean("isTablet");
        id = getInfo.getLong("id");
        quest = getInfo.getString("QustCon");
        ansA = getInfo.getString("textCon1");
        ansB = getInfo.getString("textCon2");
        ansC = getInfo.getString("textCon3");
        ansD = getInfo.getString("textCon4");

        QustCon.setText(quest);
        textConA.setText(ansA);
        textConB.setText(ansB);
        textConC.setText(ansC);
        textConD.setText(ansD);
        //correct answer
        croans= getInfo.getString("corans");
        //set RadioButton
        if(croans.equals(ansA))
            rbA.setChecked(true);
        else if(croans.equals(ansB))
            rbB.setChecked(true);
        else if(croans.equals(ansC))
            rbC.setChecked(true);
        else
            rbD.setChecked(true);

        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet) {
                    DatabaseHelper mydb = new DatabaseHelper(getActivity());
                    writableDB = mydb.getWritableDatabase();
                    writableDB.delete(mydb.Mtil_TABLE, mydb.Mtil_ID + "=" + id, null);
                    getActivity().finish();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    getActivity().setResult(11, intent);
                    getActivity().finish();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditeMultiQuest.class);
                intent.putExtra("id", id);
                intent.putExtra("quest", quest);
                intent.putExtra("ans1", ansA);
                intent.putExtra("ans2", ansB);
                intent.putExtra("ans3", ansC);
                intent.putExtra("ans4", ansD);
                intent.putExtra("croans", croans);
                startActivity(intent);
            }
        });


        return gui;
    }
}
