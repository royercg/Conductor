package com.sento40.core.utils;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class UtilsUi {

    public static void enableUI(Window window, ProgressBar pb_app) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        pb_app.setVisibility(View.INVISIBLE);
    }

    public static void disableUI(Window window, ProgressBar pb_app) {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        pb_app.setVisibility(View.VISIBLE);
    }
}
