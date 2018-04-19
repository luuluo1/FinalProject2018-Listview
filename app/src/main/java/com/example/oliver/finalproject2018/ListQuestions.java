package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import static android.view.View.VISIBLE;

public class ListQuestions extends Activity {
    protected static final String ACTIVITY_NAME = "ListQuestions";
    private ListView MultiQuest;
    private ListView NumQuest;
    private ListView TFQuest;
    private Button add;
    private ProgressBar pb;
    private FrameLayout fl;
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private Cursor cursor_m;
    private Cursor cursor_tf;
    private Cursor cursor_n;
    private Bundle infoToPass;
    MultiAdapter multiAdapter;

    ArrayList<String> content_m = new ArrayList<>();
    ArrayList<String> content_tf = new ArrayList<>();
    ArrayList<String> content_n = new ArrayList<>();

    private boolean isTablet;
    private String type="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        NumQuest = (ListView) findViewById(R.id.NumQuesView);
        TFQuest = (ListView) findViewById(R.id.TFQuesView);
        MultiQuest = (ListView) findViewById(R.id.MultiQuesView);
        infoToPass = new Bundle();

        add = (Button) findViewById(R.id.add);
        pb=(ProgressBar) findViewById(R.id.pb);
        fl = (FrameLayout) findViewById(R.id.fl);
        isTablet = (fl != null);
        //download from xml
        pb.setVisibility(VISIBLE);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();
        Log.i("FrameLayout", "avaliable: " + isTablet);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dialog
                final View conView = getLayoutInflater().inflate(R.layout.dialog_choose, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListQuestions.this);
                builder.setView(conView);
                Button addMultiQuest = (Button)conView.findViewById(R.id.addMultiQuest);
                Button addNumQuest = (Button)conView.findViewById(R.id.addNumQuest);
                Button addTFQuest = (Button)conView.findViewById(R.id.addTFQuest);

                addMultiQuest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListQuestions.this, EditeMultiQuest.class);
                        startActivity(intent);
                    }
                });

                addNumQuest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListQuestions.this, EditeNumQuest.class);
                        startActivity(intent);
                    }
                });

                addTFQuest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ListQuestions.this, EditeTFQuest.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        //show data
        showData();
        action();
        try {
            process();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showData() {
        //table1
        cursor_m = db.query(false, mydb.Mtil_TABLE, new String[]{mydb.Mtil_ID, mydb.Mtil_QUEST,mydb.Mtil_ANSA,mydb.Mtil_ANSB,mydb.Mtil_ANSC,mydb.Mtil_ANSD,mydb.Mtil_CORRT}, null, null, null, null, null, null);
        cursor_m.moveToFirst();
        while (!cursor_m.isAfterLast()) {
            content_m.add(cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_QUEST)));
            cursor_m.moveToNext();
        }
        multiAdapter=new MultiAdapter(this,R.layout.multi_row,R.id.MultiQuesView,content_m);
        MultiQuest.setAdapter(multiAdapter);
        multiAdapter.notifyDataSetChanged();

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content_m);

        //table2
        cursor_tf = db.query(false, mydb.TF_TABLE, new String[]{mydb.TF_ID, mydb.TF_QUEST,mydb.TF_ANS}, null, null, null, null, null, null);
        cursor_tf.moveToFirst();
        while (!cursor_tf.isAfterLast()) {
            content_tf.add( cursor_tf.getString(cursor_tf.getColumnIndex(mydb.TF_QUEST)));
            cursor_tf.moveToNext();
        }
        TFAdapter tfAdapter =new TFAdapter(this,R.layout.tf_row,R.id.TFQuesView,content_tf);
        TFQuest.setAdapter(tfAdapter);

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content_tf);

        //table3
        cursor_n = db.query(false, mydb.Num_TABLE, new String[]{mydb.Num_ID, mydb.Num_QUEST,mydb.Num_ANS,mydb.Num_ACC}, null, null, null, null, null, null);
        cursor_n.moveToFirst();
        while (!cursor_n.isAfterLast()) {
            content_n.add(cursor_n.getString(cursor_n.getColumnIndex(mydb.Num_QUEST)));
            cursor_n.moveToNext();
        }
        NumAdapter numAdapter =new NumAdapter(this,R.layout.tf_row,R.id.TFQuesView,content_n);
        NumQuest.setAdapter(numAdapter);

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content_n);
    }
    public void process() throws InterruptedException {
        int total = cursor_m.getCount()+ cursor_tf.getCount()+ cursor_n.getCount();
        for( int i=1; i<=total; i++){
            pb.setProgress(i/total*100);
            if(i== cursor_m.getCount()){
                pb.setProgress(100);
                //pb.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }
    public ArrayAdapter getAdapter(){
          return multiAdapter;
    }
    //set item action
    public void action(){
        //click list items
        MultiQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor_m.moveToPosition(position);
                id = cursor_m.getLong(cursor_m.getColumnIndex(mydb.Mtil_ID));
                String quest = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_QUEST));
                String ansA = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_ANSA));
                String ansB = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_ANSB));
                String ansC = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_ANSC));
                String ansD = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_ANSD));
                String corans = cursor_m.getString(cursor_m.getColumnIndex(mydb.Mtil_CORRT));
                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("QustCon", quest);
                infoToPass.putString("textConA", ansA);
                infoToPass.putString("textConB", ansB);
                infoToPass.putString("textConC", ansC);
                infoToPass.putString("textConD", ansD);
                infoToPass.putString("corans", corans);
                infoToPass.putLong("id", id);
                type = "isMulti";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MultiFragment df = new MultiFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 3);
                }
            }
        });

        //click list items
        NumQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor_n.moveToPosition(position);
                id = cursor_n.getLong(cursor_n.getColumnIndex(mydb.Num_ID));
                String quest = cursor_n.getString(cursor_n.getColumnIndex(mydb.Num_QUEST));
                String ans = cursor_n.getString(cursor_n.getColumnIndex(mydb.Num_ANS));
                String acc = cursor_n.getString(cursor_n.getColumnIndex(mydb.Num_ACC));

                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("NumCon", quest);
                infoToPass.putString("NumAns", ans);
                infoToPass.putString("NumAcc", acc);
                infoToPass.putLong("id", id);
                type = "isNum";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    NumFragment df = new NumFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 4);
                }
            }
        });

        //click list items
        TFQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor_tf.moveToPosition(position);
                id = cursor_tf.getLong(cursor_tf.getColumnIndex(mydb.TF_ID));
                String quest = cursor_tf.getString(cursor_tf.getColumnIndex(mydb.TF_QUEST));
                String ans = cursor_tf.getString(cursor_tf.getColumnIndex(mydb.TF_ANS));

                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("TFCon", quest);
                infoToPass.putString("TFAns", ans);
                infoToPass.putLong("id", id);
                type = "isTF";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TFFragment df = new TFFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 5);
                }
            }
        });
    }
    //delete data on phone and renew list

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 11) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.Mtil_TABLE, mydb.Mtil_ID + "=" + id, null);
            Log.i("Mtil_TABLE", id + " is deleted");
        }

        if (resultCode == 12) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.Num_TABLE, mydb.Num_ID + "=" + id, null);
            Log.i("Num_TABLE", id + " is deleted");
        }

        if (resultCode == 13) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.TF_TABLE, mydb.TF_ID + "=" + id, null);
            Log.i("TF_TABLE", id + " is deleted");
        }

        //renew list
        content_m.clear();
        content_tf.clear();
        content_n.clear();
        showData();
        LinearLayout lay = (LinearLayout) findViewById(R.id.lay);
        Snackbar.make(lay, "delete successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
