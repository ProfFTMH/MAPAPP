package com.example.fatemeh.mffb;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.color;
import static android.R.attr.data;
import static android.R.attr.editTextColor;


public class Addpill extends Activity {
    int color=1;
    String Url;
    String[] web1 = {"1", "2", "3", "4", "5", "6", "7"};
    private WebView webView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpill);
        final Spinner sp = (Spinner) findViewById(R.id.spinner);
        final Button btn5 = (Button) findViewById(R.id.button5);
        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText time = (EditText) findViewById(R.id.editText3);
        final EditText perid = (EditText) findViewById(R.id.editText4);
        final EditText numofpill = (EditText) findViewById(R.id.editText6);
//        final ImageView wait = (ImageView) findViewById(R.id.wait);
//        final WebView write=(WebView) findViewById(R.id.write);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, web1);
        adapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {

            @Override
            public void onItemSelected (AdapterView < ? > arg0, View arg1,
                                        int position, long id) {
                String box = sp.getSelectedItem().toString();
//                        String box = web1[position];
                Toast.makeText(Addpill.this, box, Toast.LENGTH_LONG).show();

            }


//

            @Override
            public void onNothingSelected (AdapterView < ? > arg0){

            }
        });




//       inal Addpill.HttpGetRequest getRequest = new Addpill.HttpGetRequest();

        btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                 TODO Auto-generated method stub

                String colour;
                if ((color % 2)==0){
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
                String box = sp.getSelectedItem().toString();
                color++;
                if(mary==4) {
                    btn5.setBackgroundResource(R.drawable.waitanim);
                    AnimationDrawable anim44 = (AnimationDrawable) btn5.getBackground();
                    anim44.start();
                    anim44.setOneShot(false);

                    if (box.equals("1")) {
                        Url = "http://thingtalk.ir/update?key=5YG96FBAOBKCDFBS&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (box.equals("2")) {
                        Url = "http://thingtalk.ir/update?key=HCLOSYZURVB3OGES&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (box.equals("3")) {
                        Url = "http://thingtalk.ir/update?key=HS65QMRNYC5W0SI6&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (box.equals("4")) {
                        Url = "http://thingtalk.ir/update?key=WR9X3ECRFPMPQG8Y&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (box.equals("5")) {
                        Url = "http://thingtalk.ir/update?key=29BUPAVVBC865G4E&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (box.equals("6")) {
                        Url = "http://thingtalk.ir/update?key=EP003ETITIY23PCB&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (box.equals("7")) {
                        Url = "http://thingtalk.ir/update?key=OLRTIVGX6FFX7EK4&field1=";
                        Log.e("ccc", "dddddd");
                    }


                    String myUrl = Url + starttime + "&field2=" + period + "&field3=" + num + "&field4=1&field5=0&field6=" + namepill;
                    WebView webView1 = (WebView) findViewById(R.id.write);
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
                            startActivity(new Intent(Addpill.this, pillsdetail.class));
                            try {
                                finish();
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }, 4000);
                }
//
//
//                try {
//
//                    String result = getRequest.execute(myUrl).get();
//                    JSONObject jo = new JSONObject(result);
//                    name.setText(result);
//                    Log.e("AAAA","BBBBBBBBB");
////                     startActivity(new Intent(Addpill.this, pillsdetail.class));
//                     }
//                     catch (Exception e){}
            }
        });


        Button btn22 = (Button) findViewById(R.id.button6);
        btn22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startActivity(new Intent(Addpill.this, pillsdetail.class));
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
//
//    private class HttpGetRequest extends AsyncTask<String, Void, String> {
//        public static final String REQUEST_METHOD = "GET";
//        public static final int READ_TIMEOUT = 15000;
//        public static final int CONNECTION_TIMEOUT = 15000;
//
//        @Override
//        protected String doInBackground(String... params){
//            String stringUrl = params[0];
//            String result;
//            String inputLine;
//
//            try {
//                //Create a URL object holding our url
//                URL myUrl = new URL(stringUrl);
//
//                //Create a connection
//                HttpURLConnection connection =(HttpURLConnection)
//                        myUrl.openConnection();
//
//                //Set methods and timeouts
//                connection.setRequestMethod(REQUEST_METHOD);
//                connection.setReadTimeout(READ_TIMEOUT);
//                connection.setConnectTimeout(CONNECTION_TIMEOUT);
//
//                //Connect to our url
//                connection.connect();
//
//                //Create a new InputStreamReader
//                InputStreamReader streamReader = new
//                        InputStreamReader(connection.getInputStream());
//
//                //Create a new buffered reader and String Builder
//                BufferedReader reader = new BufferedReader(streamReader);
//                StringBuilder stringBuilder = new StringBuilder();
//
//                //Check if the line we are reading is not null
//                while((inputLine = reader.readLine()) != null){
//                    stringBuilder.append(inputLine);
//                }
//
//                //Close our InputStream and Buffered reader
//                reader.close();
//                streamReader.close();
//
//                //Set our result equal to our stringBuilder
//                result = stringBuilder.toString();
//            }
//            catch(IOException e){
//                e.printStackTrace();
//                result = null;
//            }
//
//            return result;
//        }
//
//        protected void onPostExecute(String result){
//            super.onPostExecute(result);
//        }
//
//
//    }

}

