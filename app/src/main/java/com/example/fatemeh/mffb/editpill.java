package com.example.fatemeh.mffb;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class editpill extends AppCompatActivity {
    
    int color1=1;
    String Url;
    String Urlw;

    String state;
    String my_key_number;
    int x;
    String pilll ;
    String periodd;
    String stringnum;
    String stringuse;
    String  strt;
    String enable;
    String p;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpill);
        final Button btn35 = (Button) findViewById(R.id.button35);
        Button btn36 = (Button) findViewById(R.id.button36);
        final EditText name = (EditText) findViewById(R.id.editText333);
        final EditText time = (EditText) findViewById(R.id.editText33);
        final EditText perid = (EditText) findViewById(R.id.editText34);
        final EditText numofpill = (EditText) findViewById(R.id.editText36);
        final EditText numofusedpill = (EditText) findViewById(R.id.editText336);
        final Switch  ena =(Switch)findViewById(R.id.switch1);
//        final ImageView wait = (ImageView) findViewById(R.id.wait3);
        editpill.HttpGetRequest getRequest = new editpill.HttpGetRequest();

        x = Integer.valueOf(getIntent().getExtras().getString("DataName"))+1;
        my_key_number= String.valueOf(x);

        if (my_key_number.equals("1")) {
            Url = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
            Log.e("ccc", "dddddd");
        }
        if (my_key_number.equals("2")) {
            Url = "http://thingtalk.ir/channels/489/feed.json?key=HCLOSYZURVB3OGES&results=1";
            Log.e("eeee", "fffffff");
        }
        if (my_key_number.equals("3")) {
            Url = "http://thingtalk.ir/channels/490/feed.json?key=HS65QMRNYC5W0SI6&results=1";
            Log.e("ccc", "dddddd");
        }
        if (my_key_number.equals("4")) {
            Url = "http://thingtalk.ir/channels/491/feed.json?key=WR9X3ECRFPMPQG8Y&results=1";
            Log.e("eeee", "fffffff");
        }
        if (my_key_number.equals("5")) {
            Url = "http://thingtalk.ir/channels/492/feed.json?key=29BUPAVVBC865G4E&results=1";
            Log.e("ccc", "dddddd");
        }
        if (my_key_number.equals("6")) {
            Url = "http://thingtalk.ir/channels/493/feed.json?key=EP003ETITIY23PCB&results=1";
            Log.e("eeee", "fffffff");
        }
        if (my_key_number.equals("7")) {
            Url = "http://thingtalk.ir/channels/494/feed.json?key=OLRTIVGX6FFX7EK4&results=1";
            Log.e("ccc", "dddddd");
        }


        try {
            String result = getRequest.execute(Url).get();
            JSONObject jo0 = new JSONObject(result);
            JSONArray feed0 = jo0.getJSONArray("feeds");
            JSONObject data = feed0.getJSONObject(0);
            pilll = data.getString("field6");
            periodd = data.getString("field2");
            p=periodd;
            stringnum=data.getString("field3");
            stringuse=data.getString("field5");
            strt=data.getString("field1");
            enable =data.getString("field4");
            name.setHint(pilll);
            time.setHint(strt);
            perid.setHint(periodd);
            numofpill.setHint(stringnum);
            numofusedpill.setHint(stringuse);

            if (enable.equals("1")) {
                ena.setChecked(true);
                ena.setText("enable");
                ena.setTextColor(Color.parseColor("#00ff1d"));
                state="1";
            }

            if (enable.equals("0")) {
                ena.setChecked(false);
                ena.setText("disable");
                ena.setTextColor(Color.parseColor("#ff0000"));
                state="0";
            }






        } catch (Exception e) {
        }

        ena.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    ena.setText("enable");
                    ena.setTextColor(Color.parseColor("#00ff1d"));
                    state="1";
                }
                else{
                    ena.setText("disable");
                    ena.setTextColor(Color.parseColor("#ff0000"));
                    state="0";
                }


            }
        });




        btn35.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
//                 TODO Auto-generated method stub

                String colour;
                if ((color1 % 2)==0){
                    colour="#f95f00";


                }
                else {
                    colour="#991515";
                }

                int mary=0;
                final String namepill = name.getText().toString();
                if (namepill.trim().equals("")){
                    name.setHint("Enter NAME");
                    name.setHintTextColor(Color.parseColor(colour));
                }
                else{
                    mary++;
                }
                final String starttime = time.getText().toString();
                if (starttime.trim().equals("")){
                    time.setHint("Enter start time");
                    time.setHintTextColor(Color.parseColor(colour));
                }
                else {
                    mary++;
                }
                final String period = perid.getText().toString();
                if (period.trim().equals("")){
                    perid.setHint("Enter period");
                    perid.setHintTextColor(Color.parseColor(colour));
                }
                else {
                    mary++;
                }
                final String num = numofpill.getText().toString();
                if (num.trim().equals("")){
                    numofpill.setHint("Enter number of pills");
                    numofpill.setHintTextColor(Color.parseColor(colour));
                }
                else {
                    mary++;
                }
                final String usenum = numofusedpill.getText().toString();
                if (num.trim().equals("")){
                    numofusedpill.setHint("Enter number of used pills");
                    numofusedpill.setHintTextColor(Color.parseColor(colour));
                }
                else {
                    mary++;
                }

                color1++;
                if(mary==5) {
                    btn35.setBackgroundResource(R.drawable.editwaitanim);
//                    btn35.setBackground(null);
                    AnimationDrawable animwait = (AnimationDrawable) btn35.getBackground();
                    animwait.start();
                    animwait.setOneShot(false);

                    if (my_key_number.equals("1")) {
                        Urlw = "http://thingtalk.ir/update?key=5YG96FBAOBKCDFBS&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("2")) {
                        Urlw = "http://thingtalk.ir/update?key=HCLOSYZURVB3OGES&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("3")) {
                        Urlw = "http://thingtalk.ir/update?key=HS65QMRNYC5W0SI6&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("4")) {
                        Urlw = "http://thingtalk.ir/update?key=WR9X3ECRFPMPQG8Y&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("5")) {
                        Urlw = "http://thingtalk.ir/update?key=29BUPAVVBC865G4E&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("6")) {
                        Urlw = "http://thingtalk.ir/update?key=EP003ETITIY23PCB&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("7")) {
                        Urlw = "http://thingtalk.ir/update?key=OLRTIVGX6FFX7EK4&field1=";
                        Log.e("ccc", "dddddd");
                    }


                    String myUrl = Urlw + starttime + "&field2=" + period + "&field3=" + num + "&field4="+state+"&field5="+usenum+"&field6=" + namepill;



                    WebView webView1 = (WebView) findViewById(R.id.write3);
                    webView1.setWebViewClient(new WebViewClient());
                    webView1.getSettings().setJavaScriptEnabled(true);
                    webView1.getSettings().setLoadWithOverviewMode(true);
                    webView1.getSettings().setUseWideViewPort(true);
                    webView1.getSettings().setBuiltInZoomControls(true);
                    webView1.loadUrl(myUrl);

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(editpill.this, pillsdetail.class));
                            try {
                                finish();
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }, 4000);
                }
                else{

                    Handler h1 = new Handler();
                    h1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            name.setHintTextColor(Color.parseColor("#808080"));
                            name.setHint(pilll);
                            time.setHintTextColor(Color.parseColor("#808080"));
                            time.setHint(strt);
                            perid.setHintTextColor(Color.parseColor("#808080"));
                            perid.setHint(p);
                            numofpill.setHintTextColor(Color.parseColor("#808080"));
                            numofpill.setHint(stringnum);
                            numofusedpill.setHintTextColor(Color.parseColor("#808080"));
                            numofusedpill.setHint(stringuse);
                        }
                    }, 3000);


                }

            }
        });

        btn36.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(editpill.this, Searchpill.class);
                String s=String.valueOf(x-1);
                intent.putExtra("DataName" ,s );
                startActivity(intent );
                try {
                    finish();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        
        
    }

    /*----------*/

    private class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;

            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);

                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();

                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();

                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }

            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }

    }
}
