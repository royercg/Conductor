package com.sento40.iotaxiconductor.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sento40.iotaxiconductor.Interfaces.ConfigInterface;
import com.sento40.iotaxiconductor.Presenters.ConfigPresenter;
import com.sento40.iotaxiconductor.R;
import com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver;

import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_NOTIFICATION;
import static com.sento40.core.utils.UtilsConstants._EVENT_TYPE;

public class UtilFirebaseMessagingService extends FirebaseMessagingService implements ConfigInterface.View {

    public static final String TAG = UtilFirebaseMessagingService.class.getName();

    private ConfigInterface.Presenter presenter;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public UtilFirebaseMessagingService() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        presenter = new ConfigPresenter(this);
    }

    @Override
    public void onNewToken(String token) {
        presenter.updateTokenFcmResult(firebaseFirestore, firebaseAuth, token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        handleNow();

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");

        Intent intent = new Intent(UtilBroadcastReceiver.FILTER_NAME);
        Bundle bundle = new Bundle();
        bundle.putInt(_EVENT_TYPE, _BROADCAST_EVENT_NOTIFICATION);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    @Override
    public void getTokenFcmResult(String token) {

    }

    @Override
    public void updateTokenFcmResult() {
        Toast.makeText(this, R.string.app_toast_config_finish, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
