package com.sento40.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.sento40.core.interfaces.ISessionPreferences;

/**
 * @author RC- mayo 29, 2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public class UtilsSessionPreferences implements ISessionPreferences {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static final String _FCM_ID = "_FCM_ID";

    @SuppressLint("CommitPrefEdits")
    public UtilsSessionPreferences(Context context) {
        final String PREFERENCE = "IOTAXI_PREFERENCE";
        sharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public String getFcmId() {
        return sharedPreferences.getString(_FCM_ID, "");
    }

    @Override
    public void setFcmId(String fcmId) {
        editor.putString(_FCM_ID, fcmId);
        editor.commit();
    }
}
