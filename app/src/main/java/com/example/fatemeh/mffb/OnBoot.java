package com.example.fatemeh.mffb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Fatemeh on 9/10/2017.
 */

public class OnBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // Create Intent
        Intent serviceIntent = new Intent(context, notificationservice.class);
        // Start service
        context.startService(serviceIntent);

    }


}
