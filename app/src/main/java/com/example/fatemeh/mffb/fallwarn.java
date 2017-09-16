package com.example.fatemeh.mffb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.fastaccess.permission.base.PermissionHelper;

public class fallwarn extends AppCompatActivity {

    Button buttoncall;
    private static final int REQUEST_PHONE_CALL = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallwarn);
        Button button = (Button)findViewById(R.id.button8);
        buttoncall = (Button)findViewById(R.id.button9);
        Button buttonignore = (Button)findViewById(R.id.button10);
        String name = getIntent().getExtras().getString("Name");
        TextView who= (TextView)findViewById(R.id.textView13) ;
        who.setText(name);
        Animation animation = new AlphaAnimation(1, 0); // بین 1 و 0 یعنی بصورت کاملا پیدا و کاملا ناپیدا
        animation.setDuration(500); // مدت زمان انجام یک بار فرآیند
        animation.setInterpolator(new LinearInterpolator()); // جلوگیری از تغییرات اتوماتیک انیمیشن
        animation.setRepeatCount(Animation.INFINITE); // انجام دادن انیمیشن به اندازه بینهایت
        animation.setRepeatMode(Animation.REVERSE); // برعکس انجام دادن انیمیشن وقتی یکبار انجام شد
        button.setAnimation(animation);

//        String PERMISSION = Manifest.permission.READ_CALENDAR;
//        PermissionHelper permissionHelper = PermissionHelper.getInstance(this);
//        permissionHelper.setForceAccepting(false).request(PERMISSION);


        final Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+989359499456"));



        buttonignore.setOnClickListener(new View.OnClickListener(){

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





        buttoncall.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub


                            try {
//                                String number = "tel:" + "09359499456";
//                                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
//                                startActivity(callIntent);


                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (ContextCompat.checkSelfPermission(fallwarn.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(fallwarn.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                                    }
                                    else
                                    {
                                        startActivity(intent);
                                    }
                                }
                                else
                                {
                                    startActivity(intent);
                                }


//                    startActivity(callIntent);
                            } catch (SecurityException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }

                    });


        }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+09359499456"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(intent);
                    }
                }
            }
        }
    }


    }



