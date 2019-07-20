package com.sento40.core.base.rules;

import android.content.Context;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.QuickRule;

import java.util.Objects;

/**
 * @author AP - April 23,2019.
 * @version 0.1.0
 * @since 0.1.0
 */

public class EmailRule extends QuickRule<TextInputLayout> {

    private int messageResourceId;

    public EmailRule(int sequence, int messageResourceId) {
        super(sequence);
        this.messageResourceId = messageResourceId;
    }

    @Override
    public String getMessage(Context context) {
        return context.getString(messageResourceId);
    }

    @Override
    public boolean isValid(TextInputLayout view) {
        final String input = Objects.requireNonNull(view.getEditText()).getText().toString();
        return Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }
}
