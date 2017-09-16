package com.example.fatemeh.mffb;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.view.KeyEvent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Searchpill extends Activity {
    private WebView webView;
    String  Url;
    String my_key_number;
    String pill;
    String period;
    String stringnum;
    String stringuse;
    String staaaart;
    int num;
    int use;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpill);

        Button btn1 = (Button) findViewById(R.id.button7);
        final Button delete = (Button) findViewById(R.id.button15);
        Button edit = (Button) findViewById(R.id.button16);
        Searchpill.HttpGetRequest getRequest = new Searchpill.HttpGetRequest();


        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent= new Intent(Searchpill.this,editpill.class);
                intent.putExtra("DataName" ,my_key_number );
                startActivity(intent);
//                startActivity(new Intent(Searchpill.this, Addpill.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                delete.setBackgroundResource(R.drawable.deletewaitanim);
                AnimationDrawable anim444 = (AnimationDrawable) delete.getBackground();
                anim444.start();
                anim444.setOneShot(false);


                Bundle  extras = getIntent().getExtras();
                if (extras != null) {
                    String my_key_number = getIntent().getExtras().getString("DataName");
                   int u = Integer.valueOf(getIntent().getExtras().getString("DataName"))+1;
                    my_key_number= String.valueOf(u);

                    if (my_key_number.equals("1")) {
                        Url = "http://thingtalk.ir/update?key=5YG96FBAOBKCDFBS&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("2")) {
                        Url = "http://thingtalk.ir/update?key=HCLOSYZURVB3OGES&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("3")) {
                        Url = "http://thingtalk.ir/update?key=HS65QMRNYC5W0SI6&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("4")) {
                        Url = "http://thingtalk.ir/update?key=WR9X3ECRFPMPQG8Y&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("5")) {
                        Url = "http://thingtalk.ir/update?key=29BUPAVVBC865G4E&field1=";
                        Log.e("ccc", "dddddd");
                    }
                    if (my_key_number.equals("6")) {
                        Url = "http://thingtalk.ir/update?key=EP003ETITIY23PCB&field1=";
                        Log.e("eeee", "fffffff");
                    }
                    if (my_key_number.equals("7")) {
                        Url = "http://thingtalk.ir/update?key=OLRTIVGX6FFX7EK4&field1=";
                        Log.e("ccc", "dddddd");
                    }

                    String myUrl = Url + staaaart + "&field2=" + period + "&field3=" + stringnum + "&field4=0&field5="+stringuse+"&field6=" + pill;
                        WebView dlt = (WebView) findViewById(R.id.dlt);
                        dlt.setWebViewClient(new WebViewClient());
                        dlt.getSettings().setJavaScriptEnabled(true);
                        dlt.getSettings().setLoadWithOverviewMode(true);
                        dlt.getSettings().setUseWideViewPort(true);
                        dlt.getSettings().setBuiltInZoomControls(true);
                        dlt.loadUrl(myUrl);


                }

                Handler h2 = new Handler();
                h2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      startActivity(new Intent(Searchpill.this, pillsdetail.class));
                        finish();
                    }
                }, 2000);

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startActivity(new Intent(Searchpill.this, pillsdetail.class));
                try {
                    finish();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            String my_key_number = extras.getString("key_number");
//            String my_key_number =getIntent().getExtras().getString("DataName");
             my_key_number = getIntent().getExtras().getString("DataName");
            Toast.makeText(Searchpill.this, my_key_number, Toast.LENGTH_LONG).show();

            if (my_key_number.equals("0")) {
                Url = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
                Log.e("ccc", "dddddd");
            }
            if (my_key_number.equals("1")) {
                Url = "http://thingtalk.ir/channels/489/feed.json?key=HCLOSYZURVB3OGES&results=1";
                Log.e("eeee", "fffffff");
            }
            if (my_key_number.equals("2")) {
                Url = "http://thingtalk.ir/channels/490/feed.json?key=HS65QMRNYC5W0SI6&results=1";
                Log.e("ccc", "dddddd");
            }
            if (my_key_number.equals("3")) {
                Url = "http://thingtalk.ir/channels/491/feed.json?key=WR9X3ECRFPMPQG8Y&results=1";
                Log.e("eeee", "fffffff");
            }
            if (my_key_number.equals("4")) {
                Url = "http://thingtalk.ir/channels/492/feed.json?key=29BUPAVVBC865G4E&results=1";
                Log.e("ccc", "dddddd");
            }
            if (my_key_number.equals("5")) {
                Url = "http://thingtalk.ir/channels/493/feed.json?key=EP003ETITIY23PCB&results=1";
                Log.e("eeee", "fffffff");
            }
            if (my_key_number.equals("6")) {
                Url = "http://thingtalk.ir/channels/494/feed.json?key=OLRTIVGX6FFX7EK4&results=1";
                Log.e("ccc", "dddddd");
            }


//
//            //Perform the doInBackground method, passing in our url
            try {
                String result = getRequest.execute(Url).get();
//                String resultcontrol = getRequest.execute(control).get();
                JSONObject jo0 = new JSONObject(result);
                JSONArray feed0 = jo0.getJSONArray("feeds");
                JSONObject data = feed0.getJSONObject(0);
                 pill = data.getString("field6");
                 staaaart = data.getString("field1");
                 period = data.getString("field2");
                 stringnum=data.getString("field3");
                 stringuse=data.getString("field5");
                if(stringnum.equals("")) {
                    num=0;
                }
                else{
                    num = Integer.parseInt(stringnum);
                }
                if(stringnum.equals("")) {
                    use=0;
                }
                else{
                    use = Integer.parseInt(stringuse);
                }
                int remind = num-use;
                String enable =data.getString("field4");

                String my_message =  my_key_number;

                if (enable.equals("1")) {
                    TextView tv = (TextView) findViewById(R.id.textView1);
                    tv.setText(pill);
                    TextView tv1 = (TextView) findViewById(R.id.textView2);
                    tv1.setText("every " + period + " hours");
                    TextView tv3 = (TextView) findViewById(R.id.textView3);
                    tv3.setText(remind + " remind");
                    String site = " https://en.wikipedia.org/wiki/" + pill;

//                    String site = " https://en.wikipedia.org/wiki/" + my_key_number;
                    WebView webView = (WebView) findViewById(R.id.wv1);
                    webView.setWebViewClient(new WebViewClient());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.getSettings().setBuiltInZoomControls(true);
                    webView.loadUrl(site);
                }

                else{
                    TextView tv = (TextView) findViewById(R.id.textView1);
                    tv.setText(pill + " is disabled!");
                    TextView tv1 = (TextView) findViewById(R.id.textView2);
                    tv1.setText("you can play here!");
                    TextView tv3 = (TextView) findViewById(R.id.textView3);
                    tv3.setText(" have fun ! ");
                    String site = "https://playtictactoe.org/";
                    WebView webView = (WebView) findViewById(R.id.wv1);
                    delete.setClickable(false);
                    delete.setPadding(4,4,4,4);
//                    delete.setVisibility(View.INVISIBLE);
                    delete.setBackground(this.getResources().getDrawable((R.drawable.game)));
                    webView.setWebViewClient(new WebViewClient());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setLoadWithOverviewMode(true);
                    webView.getSettings().setUseWideViewPort(true);
                    webView.getSettings().setBuiltInZoomControls(true);
                    webView.loadUrl(site);

                }
            } catch (Exception e) {
            }
        }

//        @Override
//        public boolean onKeyDown ( int keyCode, KeyEvent event){
//            if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_BACK:
//                        if (webView.canGoBack()) {
//                            webView.goBack();
//                        } else {
//                            finish();
//                        }
//                        return true;
//                }
//
//            }
//            return super.onKeyDown(keyCode, event);


//        }
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

