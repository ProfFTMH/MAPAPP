package com.example.fatemeh.mffb;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fatemeh on 9/5/2017.
 */

public class notificationservice extends Service {

//
//    Notification mBuilder =   new Notification (R.drawable.m,"map", System.currentTimeMillis());
//    mBuilder.setLatestEvenInfo()
//    Notification.Builder notify= mBuilder.setContentTitle("MISS");
//                               mBuilder.setSmallIcon(R.drawable.m); // notification icon
//                        mBuilder .setContentTitle("map!"); // title for notification
//                        mBuilder .setContentText("kelidestan.com"); // message for notification
//    //                        mBuilder .setAutoCancel(true); // clear notification after click


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // your code


         final MqttAndroidClient client;
         client=new MqttAndroidClient(getApplicationContext(),"tcp://thingtalk.ir:1883", "android");
            try {
                client.connect(null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        try {
                            client.subscribe("map1",1);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.e("hi",topic+" " + new String(message.getPayload()));
                    if(new String(message.getPayload()).equals("fall")){
                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/505/feed.json?key=3ESD879C9603KAXP&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed= jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String  F = data.getString("field1");
                        Intent myIntent = new Intent(getBaseContext(), fallwarn.class);
                        myIntent.putExtra("Name" , F);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }
//                    if(new String(message.getPayload()).equals("empty")){
//                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
//                        String myUl12 = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
//                        String result = getRequest.execute(myUl12).get();
//                        JSONObject jo = new JSONObject(result);
//                        JSONArray feed= jo.getJSONArray("feeds");
//                        JSONObject data = feed.getJSONObject(0);
//                        String what = data.getString("field6");
//                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
//                        myIntent.putExtra("Name" , what);
//                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplication().startActivity(myIntent);
//
//                    }

                    if(new String(message.getPayload()).equals("heart")){
                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/505/feed.json?key=3ESD879C9603KAXP&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed= jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String  h = data.getString("field1");
                        Intent myIntent = new Intent(getBaseContext(), dangerousbeat.class);
                        myIntent.putExtra("Name" , h);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }


//                    if(new String(message.getPayload()).equals("miss")){
                    if(new String(message.getPayload()).equals("firstpill")){

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed= jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(),(int)System.currentTimeMillis(),intent,0);
                        Notification mBuilder =   new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);

//                        NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
//                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(),(int)System.currentTimeMillis(),intent,0);
//                        Notification notification= new Notification.Builder(pillsdetail.getBaseContext())
//                            .setContentTitle("HI")
//                                .setContentText("BYE")
//                                .setSmallIcon(R.drawable.m)
//                                .setContentIntent(pi)
//                                .setAutoCancel(true)
//                                .build();
//                        mNotificationManager.notify(0, notification);
//                      t.getActivity(getBaseContext(),0,intent,Intent.FILL_IN_ACTION);
//                        mBuilder.setContentIntent(pi);
//                        mNotificationManager.notify(0, mBuilder.build());
//                        Intent myIntent = new Intent(getBaseContext(), dangerousbeat.class);
////                        myIntent.putExtra("Name" , h);
//                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplication().startActivity(myIntent);
                    }


                    if(new String(message.getPayload()).equals("Secondpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/489/feed.json?key=HCLOSYZURVB3OGES&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("thirdpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/490/feed.json?key=HS65QMRNYC5W0SI6&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("forthpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/491/feed.json?key=WR9X3ECRFPMPQG8Y&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("fifthpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/492/feed.json?key=29BUPAVVBC865G4E&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("sixthpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/493/feed.json?key=EP003ETITIY23PCB&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("seventhpill")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/494/feed.json?key=OLRTIVGX6FFX7EK4&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent intent = new Intent(getBaseContext(), pillsdetail.class);
                        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);
                        Notification mBuilder = new Notification.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.noti) // notification icon
                                .setContentTitle("missing a pill!") // title for notification
                                .setContentText(pillname) // message for notification
                                .setContentIntent(pi)
                                .setAutoCancel(true) // clear notification after click
                                .build();
                        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotificationManager.notify(0, mBuilder);
                    }

                    if(new String(message.getPayload()).equals("firstpillempty")){

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed= jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);

                        }


                    if(new String(message.getPayload()).equals("Secondpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/489/feed.json?key=HCLOSYZURVB3OGES&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                    if(new String(message.getPayload()).equals("thirdpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/490/feed.json?key=HS65QMRNYC5W0SI6&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                    if(new String(message.getPayload()).equals("forthpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/491/feed.json?key=WR9X3ECRFPMPQG8Y&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                    if(new String(message.getPayload()).equals("fifthpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/492/feed.json?key=29BUPAVVBC865G4E&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                    if(new String(message.getPayload()).equals("sixthpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/493/feed.json?key=EP003ETITIY23PCB&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                    if(new String(message.getPayload()).equals("seventhpillempty")) {

                        notificationservice.HttpGetRequest getRequest = new notificationservice.HttpGetRequest();
                        String myl1 = "http://thingtalk.ir/channels/494/feed.json?key=OLRTIVGX6FFX7EK4&results=1";
                        String result = getRequest.execute(myl1).get();
                        JSONObject jo = new JSONObject(result);
                        JSONArray feed = jo.getJSONArray("feeds");
                        JSONObject data = feed.getJSONObject(0);
                        String pillname = data.getString("field6");
                        Intent myIntent = new Intent(getBaseContext(), emptynotify.class);
                        myIntent.putExtra("Name" , pillname);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(myIntent);
                    }

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });








        return Service.START_REDELIVER_INTENT;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
