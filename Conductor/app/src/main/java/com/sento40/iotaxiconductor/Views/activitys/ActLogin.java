package com.sento40.iotaxiconductor.Views.activitys;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.sento40.core.base.BaseActivity;
import com.sento40.core.base.rules.EmailRule;
import com.sento40.core.base.rules.NotEmptyRule;
import com.sento40.core.base.rules.PasswordRule;
import com.sento40.iotaxiconductor.R;

import java.util.List;

public class ActLogin extends BaseActivity implements OnClickListener, ValidationListener {

    private FirebaseAuth firebaseAuth;

    private MaterialButton mb_login;
    private TextView tv_registry;

    private TextInputLayout til_user, til_pass;
    private TextInputEditText tiet_user, tiet_pass;

    private Validator validator;

    @Override
    protected int getLayoutRes() {
        return R.layout.app_bar;
    }

    @Override
    protected void initializeView() {
        ViewStub viewStub = findViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.act_login);
        viewStub.inflate();

        til_user = findViewById(R.id.til_user);
        tiet_user = findViewById(R.id.tiet_user);

        til_pass = findViewById(R.id.til_pass);
        tiet_pass = findViewById(R.id.tiet_pass);

        tv_registry = findViewById(R.id.tv_registry);
        mb_login = findViewById(R.id.mb_login);
    }

    @Override
    protected void setOnClickListener() {
        tv_registry.setOnClickListener(this);
        mb_login.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {
        firebaseAuth = FirebaseAuth.getInstance();
        activateToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, ActMain.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_registry:
                Intent intent = new Intent(this, ActRegistry.class);
                startActivity(intent);
                break;
            case R.id.mb_login:
                cleanErrors();
                configRules();
                validator.validate();
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            final View mView = error.getView();
            if (mView instanceof TextInputLayout) {
                ((TextInputLayout) mView).setErrorEnabled(true);
                ((TextInputLayout) mView).setError(error.getCollatedErrorMessage(this));
            }
        }
    }

    private void configRules() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        validator.put(til_user, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new EmailRule(2, R.string.app_message_invalid_input));
        validator.put(til_pass, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new PasswordRule(2, R.string.app_message_invalid_input));
    }

    private void cleanErrors() {
        til_user.setError(null);
        til_pass.setError(null);
    }
}
