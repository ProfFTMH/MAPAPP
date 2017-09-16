package com.example.fatemeh.mffb;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        ImageView logo=(ImageView)findViewById(R.id.imageView5);
        logo.setBackgroundResource(R.drawable.aboutusanim);
        logo.setImageDrawable(null);
        AnimationDrawable anim = (AnimationDrawable) logo.getBackground();
        anim.start();
        anim.setOneShot(false);
    }
}
