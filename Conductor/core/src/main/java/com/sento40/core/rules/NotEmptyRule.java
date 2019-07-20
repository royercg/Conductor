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

public class NotEmptyRule extends QuickRule<TextInputLayout> {

    private int messageResourceId;

    public NotEmptyRule(int sequence, int messageResourceId) {
        super(sequence);
        this.messageResourceId = messageResourceId;
    }

    @Override
    public boolean isValid(TextInputLayout view) {
        return !Objects.requireNonNull(view.getEditText()).getText().toString().isEmpty();
    }

    @Override
    public String getMessage(Context context) {
        return context.getString(messageResourceId);
    }
}
