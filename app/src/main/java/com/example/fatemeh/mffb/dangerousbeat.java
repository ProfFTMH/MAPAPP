package com.example.fatemeh.mffb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class dangerousbeat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangerousbeat);
        Button he = (Button) findViewById(R.id.button22);
        String name = getIntent().getExtras().getString("Name");
        Button calldb = (Button) findViewById(R.id.button23);
        Button no = (Button) findViewById(R.id.button24);
        TextView how=(TextView)findViewById(R.id.textView26);
        how.setText(name);
        Animation animation = new AlphaAnimation(1, 0); // بین 1 و 0 یعنی بصورت کاملا پیدا و کاملا ناپیدا
        animation.setDuration(500); // مدت زمان انجام یک بار فرآیند
        animation.setInterpolator(new LinearInterpolator()); // جلوگیری از تغییرات اتوماتیک انیمیشن
        animation.setRepeatCount(Animation.INFINITE); // انجام دادن انیمیشن به اندازه بینهایت
        animation.setRepeatMode(Animation.REVERSE); // برعکس انجام دادن انیمیشن وقتی یکبار انجام شد
        he.setAnimation(animation);


        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+989359499456"));


        no.setOnClickListener(new View.OnClickListener() {

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

        calldb.setOnClickListener(new View.OnClickListener() {

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






