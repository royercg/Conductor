package com.sento40.iotaxiconductor.Views;

import android.app.Application;

import com.sento40.core.utils.UtilsSessionPreferences;

/**
 * @author RC- mayo 29, 2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public class App extends Application {
    public static UtilsSessionPreferences preferences;
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = new UtilsSessionPreferences(getApplicationContext());
        app = this;
    }
}
