package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EditeNumQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeNumQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private EditText Num_quest;
    private EditText Num_answer;
    private EditText Num_ancc;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_num_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        Num_quest= (EditText) findViewById(R.id.Num_quest);
        Num_answer= (EditText) findViewById(R.id.Num_answer);
        Num_ancc= (EditText) findViewById(R.id.Num_ancc);

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String quest=Num_quest.getText().toString().trim();
                String answer=Num_answer.getText().toString().trim();
                String ancc=Num_ancc.getText().toString().trim();
                Log.i("num acc","acc "+ancc);
                if( quest.equals("")){
                    Num_quest.setError("Cannot be null");
                }
                else if(answer.equals("") || !Pattern.matches("([1-9]\\d*(\\.\\d*[1-9])?)|(0\\.\\d*[1-9])",answer)) {
                    Num_answer.setError("Cannot be null and please enter a float greater than 0");
                }
                else if(ancc.equals("") || !Pattern.matches("^[0-9]\\d*$",ancc)) {
                    Num_ancc.setError("Cannot be null and please enter a number equal or greater than 0");
                }
                else {
                    float num =  Float.parseFloat(answer);
                    int acc = Integer.parseInt(ancc);

                    //insert data
                    ContentValues cv = new ContentValues();
                    cv.put(mydb.Num_QUEST, quest);
                    cv.put(mydb.Num_ANS, String.format("%."+acc+"f", num));
                    cv.put(mydb.Num_ACC, ancc);

                    if (id == 1) {
                        db.insert(mydb.Num_TABLE, null, cv);

                        Toast toast = Toast.makeText(EditeNumQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    } else {
                        db.update(mydb.Num_TABLE, cv, "Num_ID =" + id, null);

                        Toast toast = Toast.makeText(EditeNumQuest.this, "update successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    }
                    //reset data
                    Num_quest.setText("");
                    Num_answer.setText("");
                    Num_ancc.setText("");

                    Intent intent = new Intent(EditeNumQuest.this, ListQuestions.class);
                    startActivityForResult(intent, 60);
                }
            }
        });

        cancle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditeNumQuest.this.finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        String quest = getIntent().getStringExtra("quest");
        String ans = getIntent().getStringExtra("ans");
        String acc = getIntent().getStringExtra("acc");

        id = getIntent().getLongExtra("id",1);

        Num_quest.setText(quest);
        Num_answer.setText(ans);
        Num_ancc.setText(acc);

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
