package com.bigapps.doga.sunglass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by shadyfade on 12.08.2016.
 */
public class ScreenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))//Ekran Kapalı
        {
            Log.v("$$$$$$", "In Method:  ACTION_SCREEN_OFF");

            //context.startService(new Intent(context, SunglassService.class));

        }
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))//Ekran Açık
        {
            Log.v("$$$$$$", "In Method:  ACTION_SCREEN_ON");
            //onResume() will be called.

            //  Better check for whether the screen was already locked
            //if locked, do not take any resuming action in onResume()


        }
        else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT))//Şifre doğru girildiğinde
        {
            Log.v("$$$$$$", "In Method:  ACTION_USER_PRESENT");
            //  Handle resuming events

        }

    }
}