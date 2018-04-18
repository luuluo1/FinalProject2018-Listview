package com.example.oliver.finalproject2018;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoadSamplePatient extends AppCompatActivity {

    InputStream inputStream;
    String tempStr;
    TextView temp;
    String ACTIVITY_NAME="LoadSamplePatient";
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sample_patient);

        progressBar = (ProgressBar)findViewById(R.id.progressBarLoadPatient);
        progressBar.setVisibility(View.VISIBLE);

        temp = (TextView)findViewById(R.id.intakeForm_PatientListActivity_progressBar);

        new PatientQuery().execute();
    }

    public class PatientQuery extends AsyncTask<String, Integer,String> {
        private String tempStr;

        @Override
        protected String doInBackground(String...args){
            InputStream inputStream;

            try{
                String urlString = "http://torunski.ca/CST2335/PatientList.xml";
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                inputStream = conn.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(inputStream,"UTF-8");

                int eventType = xpp.getEventType();

                //while you are not at the end of the document
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    //Are you currently at a Start Tag？
                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            if(xpp.getName().equalsIgnoreCase("patient"))
                                tempStr+=xpp.getAttributeValue(null,"value");
                            break;
                        case XmlPullParser.TEXT:
                            tempStr+=xpp.getText();
                            break;
                        case XmlPullParser.END_TAG:
                    }
                    eventType=xpp.next();

                }


            }catch (FileNotFoundException fnfe){
                Log.e(ACTIVITY_NAME,fnfe.getMessage());
            }catch (MalformedURLException mfe){
                Log.e(ACTIVITY_NAME,mfe.getMessage());
            }catch (XmlPullParserException xppe){
                Log.e(ACTIVITY_NAME,xppe.getMessage());
            }catch(IOException ioe){
                Log.e(ACTIVITY_NAME, ioe.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer ... value){
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);
        }

        @Override
        protected void onPostExecute(String args){

            temp.setText(tempStr);
        }
    }
}