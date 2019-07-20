package com.sento40.core.base.rules;

import android.content.Context;

import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.QuickRule;

import java.util.Objects;

/**
 * @author AP - April 29,2019.
 * @version 0.1.0
 * @since 0.1.0
 */
public class PasswordValidRule extends QuickRule<TextInputLayout> {
    private int messageResourceId;
    private String pass;

    public PasswordValidRule(int sequence, int messageResourceId, String pass) {
        super(sequence);
        this.messageResourceId = messageResourceId;
        this.pass = pass;
    }

    @Override
    public boolean isValid(TextInputLayout view) {
        String input = Objects.requireNonNull(view.getEditText()).getText().toString();
        return pass.equals(input);
    }

    @Override
    public String getMessage(Context context) {
        return context.getString(messageResourceId);
    }
}
