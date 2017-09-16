package com.example.fatemeh.mffb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class emptynotify extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptynotify);
        String name = getIntent().getExtras().getString("Name");
        Button empty = (Button) findViewById(R.id.button12);
        Button callds = (Button) findViewById(R.id.button13);
        Button ok = (Button) findViewById(R.id.button14);
        TextView what=(TextView)findViewById(R.id.textView16);
        what.setText(name);
        Animation animation = new AlphaAnimation(1, 0); // بین 1 و 0 یعنی بصورت کاملا پیدا و کاملا ناپیدا
        animation.setDuration(500); // مدت زمان انجام یک بار فرآیند
        animation.setInterpolator(new LinearInterpolator()); // جلوگیری از تغییرات اتوماتیک انیمیشن
        animation.setRepeatCount(Animation.INFINITE); // انجام دادن انیمیشن به اندازه بینهایت
        animation.setRepeatMode(Animation.REVERSE); // برعکس انجام دادن انیمیشن وقتی یکبار انجام شد
        empty.setAnimation(animation);

        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+989359499456"));


        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    finish();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });

        callds.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                try {
                                String number = "tel:" + "09359499456";
                                Intent callIntet = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                                startActivity(callIntet);

//                    startActivity(callIntent);
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });
        }



}


