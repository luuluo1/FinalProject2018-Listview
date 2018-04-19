package com.example.oliver.finalproject2018;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by minyiyang on 2018-04-15.
 */

public class AsyncTask extends Activity {
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        //start a thread to parse xml information
        new ForecastQuery().execute("http://torunski.ca/CST2335/QuizInstance.xml");

    }

    private class ForecastQuery extends android.os.AsyncTask<String, Integer, String> {
        private String multi_q;
        private String ans;
        private String correct;
        ArrayList<String> anwers = new ArrayList<>();

        private String num_q;
        private String num_ans;
        private String acc;

        private String tf_q;
        private String tf_ans;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                for (String siteUrl : args) {
                    URL url = new URL(siteUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream iStream = urlConnection.getInputStream();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(iStream, "UTF-8");

                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (xpp.getEventType() == XmlPullParser.START_TAG) {
                            if (xpp.getName().equals("MultipleChoiceQuestion")) {
                                correct = xpp.getAttributeValue(null, "correct");
                                Log.i("XML Message:", correct);
                                multi_q = xpp.getAttributeValue(null, "question");
                                Log.i("XML Message:", multi_q);

                            }else if(xpp.getName().equals("Answer")){
                                if(xpp.next() == XmlPullParser.TEXT){
                                    ans = xpp.getText();
                                    anwers.add(ans);
                                }
                                Log.i("XML Message:", "anss:"+ anwers);

                            }
                            else if (xpp.getName().equals("NumericQuestion")) {
                                acc = xpp.getAttributeValue(null, "accuracy");
                                Log.i("XML Message:", acc);
                                num_q = xpp.getAttributeValue(null, "question");
                                Log.i("XML Message:", num_q);
                                num_ans = xpp.getAttributeValue(null, "answer");
                                Log.i("XML Message:", num_ans);

                            } else if (xpp.getName().equals("TrueFalseQuestion")) {
                                tf_q = xpp.getAttributeValue(null, "question");
                                Log.i("XML Message:", tf_q);
                                tf_ans = xpp.getAttributeValue(null, "answer");
                                Log.i("XML Message:", tf_ans);

                            }

                        }
                        eventType = xpp.next();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return "Finished";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(String result) {

            //INSERT DATA
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            ContentValues cv3 = new ContentValues();

            cv1.put(mydb.Mtil_QUEST, multi_q);
            cv1.put(mydb.Mtil_ANSA, anwers.get(0));
            cv1.put(mydb.Mtil_ANSB, anwers.get(1));
            cv1.put(mydb.Mtil_ANSC, anwers.get(2));
            cv1.put(mydb.Mtil_ANSD, anwers.get(3));
            cv1.put(mydb.Mtil_CORRT, correct);

            cv2.put(mydb.Num_QUEST, num_q);
            cv2.put(mydb.Num_ANS, num_ans);
            cv2.put(mydb.Num_ACC, acc);

            cv3.put(mydb.TF_QUEST, tf_q);
            cv3.put(mydb.TF_ANS, tf_ans);

            db.insert(mydb.Mtil_TABLE, null, cv1);
            db.insert(mydb.Num_TABLE, null, cv2);
            db.insert(mydb.TF_TABLE, null, cv3);

            Intent intent = new Intent(AsyncTask.this, ListQuestions.class);
            startActivity(intent);
        }
    }
}
