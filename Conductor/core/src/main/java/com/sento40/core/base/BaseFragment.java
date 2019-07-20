package com.sento40.core.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.sento40.core.R;

/**
 * @author AP - April 05,2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class BaseFragment extends Fragment {

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initializeView(View view);

    protected abstract void initComponents();

    protected abstract void setOnClickListener();

    protected abstract void initPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mView = inflater.inflate(getLayoutRes(), container, false);
        initializeView(mView);
        initPresenter();
        initComponents();
        setOnClickListener();
        return mView;
    }

    protected boolean validatePermission(final String permission, final int requestCode,
            View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(getActivity(), permission)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        if (shouldShowRequestPermissionRationale(permission)) {
            Snackbar.make(view, getString(R.string.message_need_permission), Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    }).show();

        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }
        return false;
    }
}
