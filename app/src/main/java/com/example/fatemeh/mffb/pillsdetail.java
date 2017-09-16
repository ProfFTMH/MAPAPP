package com.example.fatemeh.mffb;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class pillsdetail extends ListActivity {

    public String pill;
    String period;
    int count = 0;
    public Intent intent;
    public ListView lv1;
    public String number_of_keys;
    public String[] web= {"0","1","2","3","4","5","6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pillsdetail);

        count = 0;
        Person person;
        final ArrayList<Person> persons = new ArrayList<Person>();




//        ListView listView = (ListView) findViewById(R.id.list);
//        TextView ss = (TextView) findViewById(R.id.textView2);
//
        final String[] myUrl = new String[8];
        myUrl[0] = "http://thingtalk.ir/channels/488/feed.json?key=5YG96FBAOBKCDFBS&results=1";
        myUrl[1] = "http://thingtalk.ir/channels/489/feed.json?key=HCLOSYZURVB3OGES&results=1";
        myUrl[2] = "http://thingtalk.ir/channels/490/feed.json?key=HS65QMRNYC5W0SI6&results=1";
        myUrl[3] = "http://thingtalk.ir/channels/491/feed.json?key=WR9X3ECRFPMPQG8Y&results=1";
        myUrl[4] = "http://thingtalk.ir/channels/492/feed.json?key=29BUPAVVBC865G4E&results=1";
        myUrl[5] = "http://thingtalk.ir/channels/493/feed.json?key=EP003ETITIY23PCB&results=1";
        myUrl[6] = "http://thingtalk.ir/channels/494/feed.json?key=OLRTIVGX6FFX7EK4&results=1";
        final String  control="http://thingtalk.ir/channels/486/feed.json?key=FE63DGA526H8UFDF&results=1";



        for (int j = 0; j <= 6; j++) {
            pillsdetail.HttpGetRequest getRequest = new pillsdetail.HttpGetRequest();

//
//            //Perform the doInBackground method, passing in our url
            try {
                String address= myUrl[j];
                String result = getRequest.execute(address).get();
//                String resultcontrol = getRequest.execute(control).get();
                JSONObject jo0 = new JSONObject(result);
                JSONArray feed0 = jo0.getJSONArray("feeds");
                JSONObject data = feed0.getJSONObject(0);
                pill = data.getString("field6");
                int enable = Integer.parseInt(data.getString("field4"));

                pillsdetail.HttpGetRequest getRequest1 = new pillsdetail.HttpGetRequest();
                String resultcontrol = getRequest1.execute(control).get();
                JSONObject jo11 = new JSONObject(resultcontrol);
                JSONArray feed11 = jo11.getJSONArray("feeds");
                JSONObject data11 = feed11.getJSONObject(0);
                String k=String.valueOf(j+1);
                String stateofpill = data11.getString("field"+k);
//                JSONObject jo1 = new JSONObject(s);
////                JSONArray feed1 = jo1.getJSONArray("feeds");
//                int i = feed0.length();
////                int i1= feed1.length();
//                JSONObject data = feed0.getJSONObject(j);
//                JSONObject data1 = feed1.getJSONObject(i1 - 1);
//                int enable = Integer.parseInt(data.getString("field4"));
////                if (enable == 1) {
//                    period = data.getString("field2");
//                    pill = data.getString("field6");
////                    int eat = Integer.parseInt(data1.getString("field" + i));

                    person = new Person();
                    int p=j+1;
                    if(enable==0){
                        person.setName("No usefull pill in box "+p);
                    }
                    else {
                    person.setName(pill);
                    }

                count++;
                    if (stateofpill.equals("1")) {
                        person.setAge("ok");
                      }
                    else{
                        person.setAge("missing");


                    }
                    persons.add(person);

//                    person = new Person();
//                    person.setName("Anil");
//                    person.setAge(22);
//                    persons.add(person);

//                }
//
            } catch (Exception e) {}
//
        }
        setListAdapter(new MyAdapter(this, persons));
//        for(int o=0;o<count;o++){
//            web[o]=" "+o+" ";
//        }


        Button btn8 = (Button) findViewById(R.id.button3);
        btn8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                startActivity(new Intent(pillsdetail.this, Addpill.class));
                finish();
            }
        });

        Button btn9 = (Button) findViewById(R.id.button4);
        btn9.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub

                                        String number = "tel:" + "09359499456";
                                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                                        try {
                                            startActivity(callIntent);
                                        } catch (Exception e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }
                                    }
                                });


                Mylist adapter = new Mylist(pillsdetail.this, web,null );
                ListView list = (ListView) findViewById(R.id.list);




         list = getListView();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent intent;
                String name;
//                int name = position;
//                String value = adapter.getItemAtPosition(position).toString();
//                String value = web.getItemAtPosition(position).toString;

                   name = web[position ];


//                Toast.makeText(pillsdetail.this, String.valueOf(position) ,Toast.LENGTH_LONG).show();

                int y=Integer.valueOf(name);
                String hi =persons.get(y).getName().toString();
                intent = new Intent(pillsdetail.this, Searchpill.class);
                intent.putExtra("DataName", name);
                startActivity(intent);
                finish();
                String value = adapter.getItemAtPosition(position).toString();
                switch(value) {
                    case "0":
                        intent = new Intent(pillsdetail.this, Searchpill.class);
                        startActivity(intent);

                        break;
                    case "1":
                        intent = new Intent(pillsdetail.this, Firstpage.class);
                        startActivity(intent);

                        break;
//                    case 2:
//                        intent = new Intent(pillsdetail.this,thirdActivity.class);
//                        break;
                    //add more if you have more items in listview
                    //0 is the first item 1 second and so on...

                    // assuming string and if you want to get the value on click of list item
                    // do what you intend to do on click of listview row
                }
            }
        });






////                list.setAdapter(adapter);
//                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view,
//                                            int position, long id) {
//                        Toast.makeText(pillsdetail.this, "You choose " , Toast.LENGTH_SHORT).show();
////                        String name = web[+position];
//                        Intent intent = new Intent(pillsdetail.this, Profilepage.class);
////                        intent.putExtra("DataName", name);
////                        startActivity(intent);
//
//                    }
//                });


//        setListAdapter(new MyAdapter(this,
//                android.R.layout.simple_list_item_1,
//                R.id.textView1,
//                my_items));
////
////
//        lv1 = getListView();
//        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                Intent i = new Intent(getApplicationContext(), Searchpill.class);
//                number_of_keys = String.valueOf(position + 1);
//                i.putExtra("key_number", number_of_keys);
//                startActivity(i);
//
//
//            }
////
//        });
//    }
//
//    //
////

    }


class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Person> persons;

    public MyAdapter(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);



    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(persons.get(position).getName());
        text2.setText("" + persons.get(position).getAge());

        return twoLineListItem;
    }
}

class Person {
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}

//    private class MyAdapter extends ArrayAdapter<String> {
//
//        public MyAdapter(Context context, int resource, int textViewResourceId,
//                         String[] strings) {
//            super(context, resource, textViewResourceId, strings);
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = inflater.inflate(R.layout.activity_pilllist, parent, false);
//
//            String stringName = "string_key_" + String.valueOf(position + 1);
//            int string_res_ID = getResources().getIdentifier(stringName, "string", getPackageName());
//            String my_string = getResources().getString(string_res_ID);
//            TextView tv = (TextView) row.findViewById(R.id.textView1);
////                for (int k=0;k<count;k++) {
//            tv.setText(my_string);
//            return row;
//
////               }
//
//        }
//    }
//}






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



