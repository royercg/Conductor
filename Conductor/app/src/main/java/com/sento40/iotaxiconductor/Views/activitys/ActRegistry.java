package com.sento40.iotaxiconductor.Views.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ViewStub;

import com.sento40.core.base.BaseActivity;
import com.sento40.core.base.NonSwipeableViewPager;
import com.sento40.core.base.SectionsPagerAdapter;
import com.sento40.iotaxiconductor.HelperClasses.SingletonHelper;
import com.sento40.iotaxiconductor.R;
import com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver;
import com.sento40.iotaxiconductor.Views.fragments.FragProfile;
import com.sento40.iotaxiconductor.Views.fragments.FragRegistry;

import static com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver.FILTER_NAME;
import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_CONTINUE;
import static com.sento40.core.utils.UtilsConstants._EVENT_TYPE;

public class ActRegistry extends BaseActivity {

    private NonSwipeableViewPager vp_registry;
    private SectionsPagerAdapter mAdapter;

    private BroadcastReceiver broadcastReceiver;
    private BroadcastReceiver broadcastReceiverRegistry;

    @Override
    protected int getLayoutRes() {
        return R.layout.app_bar;
    }

    @Override
    protected void initializeView() {
        ViewStub viewStub = findViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.act_registry);
        viewStub.inflate();

        vp_registry = findViewById(R.id.vp_registry);
    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {
        activateToolbarWithHomeEnabled();
        setupViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        broadcastReceiver = new UtilBroadcastReceiver();
        broadcastReceiverRegistry = new RegistryOperation();

        registerReceiver(broadcastReceiver, new IntentFilter(FILTER_NAME));
        registerReceiver(broadcastReceiverRegistry, new IntentFilter(RegistryOperation.class.getName()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        unregisterReceiver(broadcastReceiverRegistry);
    }

    @Override
    public void onBackPressed() {
        SingletonHelper.setRemoveCurrentFragment();

        if (SingletonHelper.getCurrentFragment() == -1) {
            finish();
        } else {
            vp_registry.setCurrentItem(SingletonHelper.getCurrentFragment());
        }
    }

    private void setupViewPager() {
        mAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(FragRegistry.newInstance(), FragRegistry.class.getName());
        mAdapter.addFragment(FragProfile.newInstance(), FragProfile.class.getName());
        vp_registry.setAdapter(mAdapter);
    }

    public class RegistryOperation extends BroadcastReceiver {

        private int event_type;

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            assert bundle != null;
            event_type = bundle.getInt(_EVENT_TYPE);

            if (event_type == _BROADCAST_EVENT_CONTINUE) {
                SingletonHelper.setAddCurrentFragment();
                vp_registry.setCurrentItem(SingletonHelper.getCurrentFragment());
            }
        }
    }
}
