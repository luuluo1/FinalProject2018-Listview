package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn1= (Button) findViewById(R.id.button1);
        Button btn2= (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,pif_start.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.quiz:
                Log.d("Toolbar", "cherry is selected");
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            // action with ID action_settings was selected
            case R.id.patient:
                Log.d("Toolbar", "citrus is selected");
                Intent intent1 = new Intent(StartActivity.this,pif_start.class);
                startActivity(intent1);
                break;

            case R.id.about:
                View toolbarView = (View) findViewById(R.id.toolbarView);
                Snackbar.make(toolbarView, "Version 1.0, by Minyi Yang", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            default:
                break;
        }

        return true;
    }
}
