package com.github.sagar2093.androidutils;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by Sagar Chapagain on 9/17/2017.
 * https://github.com/sagar2093
 * sagar2093@gmail.com
 */

public class Validator {

    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isEmailValid(EditText editText, Context context) {
        boolean valid = android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches();
        if (valid) {
            editText.setError(null);
        } else {
            editText.setError(context.getString(R.string.warn_invalid_email));
            editText.requestFocus();
        }
        return valid;
    }
}
