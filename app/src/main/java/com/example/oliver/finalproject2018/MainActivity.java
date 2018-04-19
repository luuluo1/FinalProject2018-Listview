package com.example.oliver.finalproject2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    private Button button_quiz;
    private Button button_xml;
    private Button button_state;
    private Button button_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_quiz= (Button) findViewById(R.id.button_quiz);
        button_xml= (Button) findViewById(R.id.button_xml);
        button_state= (Button) findViewById(R.id.button_state);
        button_help= (Button) findViewById(R.id.button_help);

        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListQuestions.class);
                startActivity(intent);
            }
        });

        button_xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AsyncTask.class);
                startActivity(intent);
            }
        });

        button_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogStatistics.class);
                startActivity(intent);
            }
        });

        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dialog
                final View conView = getLayoutInflater().inflate(R.layout.dialog_help, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(conView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy");
    }
}
