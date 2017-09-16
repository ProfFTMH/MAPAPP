package com.example.fatemeh.mffb;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.drawable.AnimationDrawable;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.widget.ImageView;
        import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent notify = new Intent(this, notificationservice.class);
        startService(notify);

        ImageView img1 = (ImageView) findViewById(R.id.imageView3);
        img1.setBackgroundResource(R.drawable.animation1);
             img1.setImageDrawable(null);
             AnimationDrawable anim1 = (AnimationDrawable) img1.getBackground();
             anim1.start();


        new CountDownTimer(6000,1000){
           @Override
            public void onTick(long millisUntilFinished){}

            private boolean isNetworkConnected() { // check internet connection
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni == null) {
                    // There are no active networks.
                    return false;
                } else
                    return true;
            }

           @Override
           public void onFinish(){
                //set the new Content of your activity
//               if(isNetworkConnected()) {
                   startActivity(new Intent(MainActivity.this,Login.class));
//               }
//               else{
//                   Toast.makeText(MainActivity.this, "no connection to internet! /n check youe setting", Toast.LENGTH_SHORT).show();
//
//               }
           }
        }.start();

   }
//
//   @Override
//   protected void onB

    @Override
    protected void onStop() {
      super.onStop();
        finish();
   }

}
