package com.sento40.core.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.sento40.core.R;

/**
 * @author AP - April 30,2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private View mView;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initializeView(View view);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutRes(), container, false);
        MaterialButton mb_cancel = mView.findViewById(R.id.mb_cancel);
        mb_cancel.setOnClickListener(this);
        return mView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog mDialog = super.onCreateDialog(savedInstanceState);

//        Objects.requireNonNull(mDialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return mDialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeView(mView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mb_cancel) {
            dismiss();
        }
    }
}
