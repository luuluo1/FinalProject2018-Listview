package com.example.oliver.finalproject2018;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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
    Snackbar snackbar;
    Toolbar t1;
    protected static final String ACTIVITY_NAME = "LoadXMLActivity";
    ProgressBar progressBar ;
    private TextView name ;
    private TextView address ;
    private TextView birthday ;
    private TextView phone;
    private TextView card;
    private TextView patientType;
    private TextView description;

    private TextView nameDC ;
    private TextView addressDC ;
    private TextView birthdayDC ;
    private TextView phoneDC;
    private TextView cardDC;
    private TextView patientTypeDC;
    private TextView descriptionDC;

    private TextView nameOP ;
    private TextView addressOP ;
    private TextView birthdayOP ;
    private TextView phoneOP;
    private TextView cardOP;
    private TextView patientTypeOP;
    private TextView descriptionOP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load_sample_patient);

        t1=(Toolbar) findViewById(R.id.load_toolbar_start);

        setSupportActionBar(t1);

        progressBar = findViewById(R.id.loadXML_progressBar);
        name = findViewById(R.id.loadXML_name);
        nameDC=findViewById(R.id.loadXML_doctor_name);
        nameOP=findViewById(R.id.loadXML_opt_name);
        address = findViewById(R.id.loadXML_address);
        addressDC=findViewById(R.id.loadXML_doctor_address);
        addressOP=findViewById(R.id.loadXML_opt_address);
        birthday = findViewById(R.id.loadXML_birthday);
        birthdayDC=findViewById(R.id.loadXML_doctor_birthday);
        birthdayOP=findViewById(R.id.loadXML_opt_birthday);
        card=findViewById(R.id.loadXML_card);
        cardDC=findViewById(R.id.loadXML_doctor_card);
        cardOP=findViewById(R.id.loadXML_opt_card);
        phone = findViewById(R.id.loadXML_phone);
        phoneDC=findViewById(R.id.loadXML_doctor_phone);
        phoneOP=findViewById(R.id.loadXML_opt_phone);
        patientType = findViewById(R.id.loadXML_type);
        patientTypeDC=findViewById(R.id.loadXML_doctor_description);
        patientTypeOP=findViewById(R.id.loadXML_opt_description);
        description=findViewById(R.id.loadXML_description);
        progressBar.setVisibility(View.VISIBLE);
        PatientQuery patient = new PatientQuery();
        String urlString = "http://torunski.ca/CST2335/PatientList.xml";
        patient.execute(urlString);
        Log.i(ACTIVITY_NAME,"In onCreate()");
    }

    public class PatientQuery extends AsyncTask<String, Integer, String> {
        // private String speed ="speed", min="min", max="max",  currentTemperature="current", type="type";
        private String docName,optName,denName,docBir,optBir,denBir,docPho,denPho,optPho,docAddr,denAddr,optAddr,descriptionn,cardd,docTy,denTy,optTy;

        protected String doInBackground(String ... args) {
            Log.i(ACTIVITY_NAME, "In doInBackground");
            try {
                URL url = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(conn.getInputStream(), null);

                int eventType = parser.getEventType();
                while(eventType!=XmlPullParser.END_DOCUMENT){
                    //Are you currently at a Start Tag？
                    int i =20;
                    switch(eventType){
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equalsIgnoreCase("patient"))
                                docName+=parser.getAttributeValue(null,"value");
                            break;
                        case XmlPullParser.TEXT:
                            docName+=parser.getText();
                            onProgressUpdate(50);
                            break;
                        case XmlPullParser.END_TAG:

                    }
                    eventType=parser.next(); Thread.sleep(30);i+=10;
                }
            }
            catch(Exception e) {e.printStackTrace();}
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            Log.i(ACTIVITY_NAME, "In onProgressUpdate");
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);}
        @Override
        protected void onPostExecute(String result) {
            nameDC.setText(docName);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_load_data,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.PIF_about:

                snackbar.make(findViewById(android.R.id.content), "Patient Intake Application By Yang Luo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.d("Toolbar","Option camera selected");
                break;
            case R.id.PIF_help:
                Toast toast = Toast.makeText(getApplicationContext(), "This Version 1.0 for patient intake system. 2018", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.Back_to_home_page:
                Intent i1=new Intent(getApplicationContext(),pif_start.class);
                startActivity(i1);
                break;


        }return true;
    }
}