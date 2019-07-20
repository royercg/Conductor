package com.sento40.core.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.sento40.core.R;
import java.util.Objects;

/**
 * @author AP - April 11,2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initializeView();

    protected abstract void setOnClickListener();

    protected abstract void initPresenter();

    protected abstract void initComponents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initializeView();
        initPresenter();
        initComponents();
        setOnClickListener();
    }

    protected Toolbar activateToolbar() {
        if (null == mToolbar) {
            mToolbar = findViewById(R.id.toolbar);
            if (null != mToolbar) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    protected void activateToolbarWithHomeEnabled() {
        activateToolbar();
        if (null != mToolbar) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
