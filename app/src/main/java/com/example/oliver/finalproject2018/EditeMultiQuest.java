package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditeMultiQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeMultiQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    Cursor cursor;
    private EditText input_quest;
    private EditText input_answerA;
    private EditText input_answerB;
    private EditText input_answerC;
    private EditText input_answerD;
    private EditText cor_answer;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_multi_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        input_quest= (EditText) findViewById(R.id.input_quest);
        input_answerA = (EditText) findViewById(R.id.input_answerA);
        input_answerB = (EditText) findViewById(R.id.input_answerB);
        input_answerC = (EditText) findViewById(R.id.input_answerC);
        input_answerD = (EditText) findViewById(R.id.input_answerD);
        cor_answer= (EditText) findViewById(R.id.cor_answer);

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //insert data
                    String quest = input_quest.getText().toString().trim();
                    String ansA = input_answerA.getText().toString().trim();
                    String ansB = input_answerB.getText().toString().trim();
                    String ansC = input_answerC.getText().toString().trim();
                    String ansD = input_answerD.getText().toString().trim();
                    String corans = cor_answer.getText().toString().trim();

                    if( quest.equals("")){
                        input_quest.setError("Cannot be null");
                    }
                    if( ansA.equals("")|| ansA.equals(ansB) || ansA.equals(ansC) || ansA.equals(ansD) ){
                        input_answerA.setError("Cannot be null or same content with others");
                    }
                    if(ansB.equals("") || ansB.equals(ansA) || ansB.equals(ansC) || ansB.equals(ansD)){
                        input_answerB.setError("Cannot be null or same content with others");
                    }
                    if(ansC.equals("") || ansC.equals(ansA) || ansC.equals(ansB) || ansC.equals(ansD)){
                        input_answerC.setError("Cannot be null or same content with others");
                    }
                    if(ansD.equals("") || ansD.equals(ansA) || ansD.equals(ansB) || ansD.equals(ansC)){
                        input_answerD.setError("Cannot be null or same content with others");
                    }
                    if (corans.equals("") || (!corans.equals(ansA) && !corans.equals(ansB) && !corans.equals(ansC) && !corans.equals(ansD) )){
                        cor_answer.setError("Invalid answer");
                    }else {
                        ContentValues cv = new ContentValues();
                        cv.put(mydb.Mtil_QUEST, quest);
                        cv.put(mydb.Mtil_ANSA, ansA);
                        cv.put(mydb.Mtil_ANSB, ansB);
                        cv.put(mydb.Mtil_ANSC, ansC);
                        cv.put(mydb.Mtil_ANSD, ansD);
                        cv.put(mydb.Mtil_CORRT, corans);

                        if (id == 1) {
                            db.insert(mydb.Mtil_TABLE, null, cv);

                            Toast toast = Toast.makeText(EditeMultiQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                            toast.show(); //display box
                        } else {
                            db.update(mydb.Mtil_TABLE, cv, "Mtil_ID =" + id, null);

                            Toast toast = Toast.makeText(EditeMultiQuest.this, "update successful", Toast.LENGTH_SHORT); //this is the ListActivity
                            toast.show(); //display box
                        }
                        //reset data
                        input_quest.setText("");
                        input_answerA.setText("");
                        input_answerB.setText("");
                        input_answerC.setText("");
                        input_answerD.setText("");
                        cor_answer.setText("");

                        Intent intent = new Intent(EditeMultiQuest.this, ListQuestions.class);
                        startActivity(intent);
                    }
                }
        });

        cancle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditeMultiQuest.this.finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        String quest = getIntent().getStringExtra("quest");
        String ans1 = getIntent().getStringExtra("ans1");
        String ans2 = getIntent().getStringExtra("ans2");
        String ans3 = getIntent().getStringExtra("ans3");
        String ans4 = getIntent().getStringExtra("ans4");
        String croans = getIntent().getStringExtra("croans");
        id = getIntent().getLongExtra("id",1);
        Log.i(ACTIVITY_NAME, "id : "+id);
        input_quest.setText(quest);
        input_answerA.setText(ans1);
        input_answerB.setText(ans2);
        input_answerC.setText(ans3);
        input_answerD.setText(ans4);
        cor_answer.setText(croans);
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
