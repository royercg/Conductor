package com.sento40.core.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author AP - May 11,2019.
 * @version 0.1.0
 * @since 0.1.0
 */
abstract public class BaseDialogServiceFragment extends BaseDialogFragment {

    protected abstract void initComponents();

    protected abstract void setOnClickListener();

    protected abstract void initPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
        initComponents();
        setOnClickListener();
    }
}
