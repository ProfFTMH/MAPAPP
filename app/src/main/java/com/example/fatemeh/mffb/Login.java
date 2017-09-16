package com.example.fatemeh.mffb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    int rank=0;
    boolean x = false;boolean y = false;
    String userresult;
    String passresult;
    int lenght;
    int i=0;
    String usr;
    String pss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginbtn = (Button) findViewById(R.id.button1);
        final EditText user = (EditText) findViewById(R.id.editText1);
        final EditText pass = (EditText) findViewById(R.id.editText2);

        final String myUrl = "http://thingtalk.ir/channels/504/feed.json?key=BCUNM11WQA0VLNKL&results=100";
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                y = false;
                HttpGetRequest getRequest = new HttpGetRequest();

                //Perform the doInBackground method, passing in our url
                try {
                    String result = getRequest.execute(myUrl).get();
//                    System.out.println(result);


                    usr = user.getText().toString();
                    pss = pass.getText().toString();
                    JSONObject jo = new JSONObject(result);
                    JSONArray feed = jo.getJSONArray("feeds");
                    lenght = feed.length();
                    for (i = 0; i < lenght; i++) {
                        JSONObject data = feed.getJSONObject(i);
                        userresult = data.getString("field1");
                        passresult = data.getString("field2");
//
                        if (userresult.equals(usr)) {
                            if (passresult.equals(pss)) {
                                y = true;
                                startActivity(new Intent(Login.this, Firstpage.class));
                            }

                        }
                    }
                    if (result != null) {
                        x = true;
                    }
//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                while (x != true) {
//                }
                user.setText(null);
                pass.setText(null);
                if (y == false) {

                    Toast.makeText(Login.this, "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
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

