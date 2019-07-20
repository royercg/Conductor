package com.sento40.iotaxiconductor.Views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.sento40.core.base.BaseFragment;
import com.sento40.core.base.rules.EmailRule;
import com.sento40.core.base.rules.NotEmptyRule;
import com.sento40.core.base.rules.PasswordRule;
import com.sento40.core.base.rules.PasswordValidRule;
import com.sento40.iotaxiconductor.Interfaces.LoginInterface;
import com.sento40.iotaxiconductor.Models.AccountBean;
import com.sento40.iotaxiconductor.Presenters.LoginPresenter;
import com.sento40.iotaxiconductor.R;
import com.sento40.iotaxiconductor.Utils.broadcast.UtilBroadcastReceiver;

import java.util.List;
import java.util.Objects;

import static com.sento40.core.utils.UtilsConstants._BROADCAST_EVENT_CONTINUE;
import static com.sento40.core.utils.UtilsConstants._EVENT_TYPE;

/**
 * @author RC - May 08, 2019
 * @version 0.1.0
 * @since 0.1.0
 * <p>
 * This activity represents a form by notifications
 */
public class FragRegistry extends BaseFragment implements OnClickListener, ValidationListener, LoginInterface.View {

    private LoginInterface.Presenter presenter;

    private TextInputLayout til_user, til_pass, til_pass_valid;
    private TextInputEditText tiet_user, tiet_pass, tiet_pass_valid;
    private MaterialButton mb_registry;

    private Validator validator;

    private FirebaseAuth firebaseAuth;

    public static FragRegistry newInstance() {
        return new FragRegistry();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_registry;
    }

    @Override
    protected void initializeView(View view) {
        til_user = view.findViewById(R.id.til_user);
        tiet_user = view.findViewById(R.id.tiet_user);

        til_pass = view.findViewById(R.id.til_pass);
        tiet_pass = view.findViewById(R.id.tiet_pass);

        til_pass_valid = view.findViewById(R.id.til_pass_valid);
        tiet_pass_valid = view.findViewById(R.id.tiet_pass_valid);

        mb_registry = view.findViewById(R.id.mb_registry);
    }

    @Override
    protected void initComponents() {
        configRules();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void setOnClickListener() {
        mb_registry.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mb_registry) {
            cleanErrors();
            removeRules();
            configRules();
            validator.validate();
        }
    }

    @Override
    public void onValidationSucceeded() {

        AccountBean accountBean = new AccountBean();
        accountBean.setUser(Objects.requireNonNull(tiet_user.getText()).toString());
        accountBean.setPass(Objects.requireNonNull(tiet_pass.getText()).toString());

        presenter.createAccount(firebaseAuth, accountBean);

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
        validator.put(til_user, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new EmailRule(2, R.string.app_message_invalid_input));
        validator.put(til_pass, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new PasswordRule(2, R.string.app_message_invalid_input));
        validator.put(til_pass_valid, new NotEmptyRule(1, R.string.app_message_invalid_input),
                new PasswordValidRule(2, R.string.app_message_invalid_input,
                        Objects.requireNonNull(tiet_pass.getText()).toString()));
    }

    private void cleanErrors() {
        til_user.setError(null);
        til_pass.setError(null);
        til_pass_valid.setError(null);
    }

    private void removeRules() {
        validator.removeRules(til_user);
        validator.removeRules(til_pass);
        validator.removeRules(til_pass_valid);
    }

    @Override
    public void createAccount() {
        Intent intent = new Intent(UtilBroadcastReceiver.FILTER_NAME);
        Bundle bundle = new Bundle();
        bundle.putInt(_EVENT_TYPE, _BROADCAST_EVENT_CONTINUE);
        intent.putExtras(bundle);

        Objects.requireNonNull(getContext()).sendBroadcast(intent);
    }

    @Override
    public void createProfile() {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
