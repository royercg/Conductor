package com.sento40.iotaxiconductor.Views.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.sento40.core.base.BaseFragment;
import com.sento40.core.base.rules.NotEmptyRule;
import com.sento40.core.base.rules.PhoneNumberRule;
import com.sento40.iotaxiconductor.Interfaces.LoginInterface;
import com.sento40.iotaxiconductor.Models.DriverBean;
import com.sento40.iotaxiconductor.Presenters.LoginPresenter;
import com.sento40.iotaxiconductor.R;
import com.sento40.iotaxiconductor.Views.activitys.ActMain;

import java.util.List;
import java.util.Objects;

/**
 * @author RC - May 08, 2019
 * @version 0.1.0
 * @since 0.1.0
 * <p>
 * This activity represents a form by notifications
 */
public class FragProfile extends BaseFragment implements Validator.ValidationListener, View.OnClickListener, LoginInterface.View {

    private LoginInterface.Presenter presenter;

    private TextInputLayout til_name, til_lastname, til_phonenumber;
    private TextInputEditText tiet_name, tiet_lastname, tiet_phonenumber;
    private MaterialButton mb_continue;

    private Validator validator;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public static FragProfile newInstance() {
        return new FragProfile();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_profile;
    }

    @Override
    protected void initializeView(View view) {
        til_name = view.findViewById(R.id.til_name);
        tiet_name = view.findViewById(R.id.tiet_name);

        til_lastname = view.findViewById(R.id.til_lastname);
        tiet_lastname = view.findViewById(R.id.tiet_lastname);

        til_phonenumber = view.findViewById(R.id.til_phonenumber);
        tiet_phonenumber = view.findViewById(R.id.tiet_phonenumber);

        mb_continue = view.findViewById(R.id.mb_continue);
    }

    @Override
    protected void initComponents() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        configRules();
    }

    @Override
    protected void setOnClickListener() {
        mb_continue.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onValidationSucceeded() {

        DriverBean driverBean = new DriverBean();
        driverBean.setUuid(firebaseAuth.getUid());
        driverBean.setName(Objects.requireNonNull(tiet_name.getText()).toString());
        driverBean.setLastName(Objects.requireNonNull(tiet_lastname.getText()).toString());
        driverBean.setPhoneNumber(Objects.requireNonNull(tiet_phonenumber.getText()).toString());

        presenter.createProfile(firebaseFirestore, driverBean);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            final View mView = error.getView();
            if (mView instanceof TextInputLayout) {
                ((TextInputLayout) mView).setErrorEnabled(true);
                ((TextInputLayout) mView).setError(error.getCollatedErrorMessage(getContext()));
            }
        }
    }

    private void configRules() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.put(til_name, new NotEmptyRule(1, R.string.app_message_invalid_input));
        validator.put(til_lastname, new NotEmptyRule(1, R.string.app_message_invalid_input));
        validator.put(til_phonenumber, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new PhoneNumberRule(2, R.string.app_message_invalid_input));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mb_continue) {
            cleanErrors();
            validator.validate();
        }
    }

    private void cleanErrors() {
        til_name.setError(null);
        til_lastname.setError(null);
        til_phonenumber.setError(null);
    }

    @Override
    public void createAccount() {

    }

    @Override
    public void createProfile() {
        Intent intent = new Intent(getContext(), ActMain.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
