package com.example.fatemeh.mffb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Profilepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
        final String Myname = getIntent().getExtras().getString("DataName");
        int resID = getResources().getIdentifier(Myname,"drawable",getPackageName());
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(resID);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        // TODO Auto-generated method stub

                startActivity(new Intent(Profilepage.this , Heartbeatdetail.class));
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        // TODO Auto-generated method stub

//                startActivity(new Intent(Profilepage.this , pillsdetail.class));

                Intent intent = new Intent(Profilepage.this, pillsdetail.class);
                intent.putExtra("DataName" , Myname);
                startActivity(intent);

            }
        });


    }

}
