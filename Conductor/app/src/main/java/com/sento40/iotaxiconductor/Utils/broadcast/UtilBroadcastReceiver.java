package com.sento40.iotaxiconductor.Utils.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sento40.iotaxiconductor.Utils.intentservices.UtilIntentService;

/**
 * @author RC - April 26, 2019
 * @version 0.1.0
 * @since 0.1.0
 * <p>
 * This activity represents a form by notifications
 */
public class UtilBroadcastReceiver extends BroadcastReceiver {

    public static final String FILTER_NAME = UtilBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, UtilIntentService.class);
        intent1.putExtras(intent);
        context.startService(intent1);
    }
}
