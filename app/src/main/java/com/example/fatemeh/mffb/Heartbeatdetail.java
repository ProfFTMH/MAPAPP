package com.example.fatemeh.mffb;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.fatemeh.mffb.R.drawable.animation0;

public class Heartbeatdetail extends Activity {

        String heartbeat;
        int x=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartbeatdetail);
        final String myUrl1 = "http://thingtalk.ir/channels/505/feed.json?key=3ESD879C9603KAXP&results=1";
        final ImageView img = (ImageView) findViewById(R.id.imageView2);
        Heartbeatdetail.HttpGetRequest getRequest = new Heartbeatdetail.HttpGetRequest();
        TextView hb = (TextView)findViewById(R.id.textView6);
        TextView hbs = (TextView)findViewById(R.id.textView9);

        //Perform the doInBackground method, passing in our url
        try {
            String result = getRequest.execute(myUrl1).get();
            JSONObject jo = new JSONObject(result);
            JSONArray feed= jo.getJSONArray("feeds");
//            int i= feed.length();
            JSONObject data = feed.getJSONObject(0);
            heartbeat = data.getString("field1");
            hb.setText(heartbeat);
            int x=Integer.parseInt(heartbeat);

            if (x>60 && x<100){
                hbs.setText("normal");
                img.setBackgroundResource(R.drawable.animation);
                img.setImageDrawable(null);
                AnimationDrawable anim = (AnimationDrawable) img.getBackground();
                anim.start();

//            runOnUiThread(new Runnable() {

//                @Override
//                public void run() {


//                }
//            });
//
//            Thread t1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//
//                    anim.start();
////                    boolean a=anim.isRunning();
//
//
//                }});
//
//            t1.start();
//            t1.join();

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override public void run() {
                        img.setBackgroundResource(R.drawable.animation0);
                        img.setImageDrawable(null);
                        AnimationDrawable anim2 = (AnimationDrawable) img.getBackground();
                        anim2.start();
                        anim2.setOneShot(false);                }
                }, 1900);

            }
            if (x<60 ) {
                hbs.setText("low");
            }
            if (x>100 ) {
                hbs.setText("high");
                img.setBackgroundResource(R.drawable.animation00);
                img.setImageDrawable(null);
                AnimationDrawable anim = (AnimationDrawable) img.getBackground();
                anim.start();


                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override public void run() {
                        img.setBackgroundResource(R.drawable.animation000);
                        img.setImageDrawable(null);
                        AnimationDrawable anim2 = (AnimationDrawable) img.getBackground();
                        anim2.start();
                        anim2.setOneShot(false);                }
                }, 1550);

            }




//            runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    }catch (Exception e){}
//                    img.setBackgroundResource(R.drawable.animation0);
//                    img.setImageDrawable(null);
//                    AnimationDrawable anim2 = (AnimationDrawable) img.getBackground();
//                    anim2.start();
//                    anim2.setOneShot(false);
//                }
//            });

//            Thread t2 = new Thread(new Runnable() {
////
//                @Override
//                public void run() {
//
//                    try{ Thread.sleep(2000); }catch(InterruptedException e){ }
//
//
//
//
//                }});



//            t2.start();

//                img.setBackgroundResource(R.drawable.animation0);
//                img.setImageDrawable(null);
//                AnimationDrawable anim2 = (AnimationDrawable) img.getBackground();
//                anim2.start();
//                anim2.setOneShot(false);


        }catch (Exception e){}

        //callbutton
        Button btncall = (Button) findViewById(R.id.button);
        btncall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

//                String[] perms={"android.permission.INTERNET"};
//                int permsrequestcode=200;
//                requestPermissions(perms,permsrequestcode);


                String number = "tel:" + "09359499456";
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                try {
                    startActivity(callIntent);
                }catch (Exception e) {
                    // TODO Auto-generated catch block

                    Log.e("call", e.getMessage());
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
