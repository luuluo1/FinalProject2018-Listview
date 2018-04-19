package com.example.oliver.finalproject2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TestToolbar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                Intent intent = new Intent(TestToolbar.this,MainActivity.class);
                startActivity(intent);
                break;
            // action with ID action_settings was selected
            case R.id.patient:
                Log.d("Toolbar", "citrus is selected");
                Intent intent1 = new Intent(TestToolbar.this,MainActivity.class);
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
