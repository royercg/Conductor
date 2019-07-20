package com.sento40.iotaxiconductor.Views.activitys;

import static com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver.FILTER_NAME;
import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_NOTIFICATION;
import static com.sento40.core.utils.UtilsConstants._EVENT_TYPE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sento40.core.base.BaseActivity;
import com.sento40.iotaxiconductor.Interfaces.ConfigInterface;
import com.sento40.iotaxiconductor.Presenters.ConfigPresenter;
import com.sento40.iotaxiconductor.R;
import com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver;
import com.sento40.iotaxiconductor.Views.dialogs.DialogRide;

public class ActMain extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, ConfigInterface.View {

    private ConfigInterface.Presenter presenter;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private BroadcastReceiver broadcastReceiver;
    private BroadcastReceiver broadcastReceiverNotification;

    @Override
    protected int getLayoutRes() {
        return R.layout.drawer_layout;
    }

    @Override
    protected void initializeView() {
        ViewStub viewStub = findViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.act_main);
        viewStub.inflate();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, activateToolbar(), R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void initPresenter() {
        presenter = new ConfigPresenter(this);
    }

    @Override
    protected void initComponents() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        presenter.getTokenFcm();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void getTokenFcmResult(String token) {
        presenter.updateTokenFcmResult(firebaseFirestore, firebaseAuth, token);
    }

    @Override
    public void updateTokenFcmResult() {
        Toast.makeText(this, R.string.app_toast_config_finish, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new UtilBroadcastReceiver();
        broadcastReceiverNotification = new NotificationOperation();

        registerReceiver(broadcastReceiver, new IntentFilter(FILTER_NAME));
        registerReceiver(broadcastReceiverNotification, new IntentFilter(NotificationOperation.class.getName()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        unregisterReceiver(broadcastReceiverNotification);
    }

    private void showNotification() {
        DialogRide dialogRide = DialogRide.newInstance();
        dialogRide.setCancelable(false);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        dialogRide.show(fragmentTransaction, DialogRide.TAG);
    }

    public class NotificationOperation extends BroadcastReceiver {

        private int event_type;

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            assert bundle != null;
            event_type = bundle.getInt(_EVENT_TYPE);

            if (event_type == _BROADCAST_EVENT_NOTIFICATION) {
                showNotification();
            }

        }
    }
}