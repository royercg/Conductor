package com.sento40.iotaxiconductor.Utils.intentservices;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.sento40.iotaxiconductor.Views.activitys.ActMain;
import com.sento40.iotaxiconductor.Views.activitys.ActRegistry;

import java.util.Objects;

import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_CONTINUE;
import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_NOTIFICATION;
import static com.sento40.core.utils.UtilsConstants._EVENT_TYPE;

/**
 * @author RC - April 26, 2019
 * @version 0.1.0
 * @since 0.1.0
 * <p>
 * This activity represents a form by notifications
 */
public class UtilIntentService extends IntentService {

    public UtilIntentService() {
        super(UtilIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent intent1 = null;

        switch (Objects.requireNonNull(Objects.requireNonNull(intent).getExtras()).getInt(_EVENT_TYPE)) {
            case _BROADCAST_EVENT_CONTINUE:
                intent1 = new Intent(ActRegistry.RegistryOperation.class.getName());
                break;
            case _BROADCAST_EVENT_NOTIFICATION:
                intent1 = new Intent(ActMain.NotificationOperation.class.getName());
                break;
        }

        Objects.requireNonNull(intent1).putExtras(intent);
        getApplication().sendBroadcast(intent1);
    }
}
