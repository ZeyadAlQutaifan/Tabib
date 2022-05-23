package validation;

import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class Validation {

    public static boolean isEmpty(@NonNull TextView[] textViews, String errorMessage) {
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i].getText().toString().isEmpty()) {
                textViews[i].setError(errorMessage);
                textViews[i].requestFocus();
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailMatchesPatter(@NonNull TextView textView, String errorMessage) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        if (!EMAIL_REGEX.matcher(textView.getText().toString()).matches()) {
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        }
        return true;
    }
    public static boolean isSSnMatchesPatter(@NonNull TextView textView, String errorMessage) {
        if (textView.getText().toString().length() != 10) {
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isPhoneMatchesPattern(@NonNull TextView textView, String errorMessage) {
        String phone = textView.getText().toString();
        if (!(phone.charAt(0) == '0' && phone.charAt(1) == '7'
                && (phone.charAt(2) == '7' || phone.charAt(2) == '8' || phone.charAt(2) == '9'))) {
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        }
        if (phone.length() != 10){
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        }

            return true;
        }




}
