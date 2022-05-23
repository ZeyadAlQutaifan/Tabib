package validation;

import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class Validation {

    public static boolean isEmpty(@NonNull TextView[] textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (textViews[i].getText().toString().isEmpty()) {
                textViews[i].setError("لا يمكن ان يكون فارغاً");
                textViews[i].requestFocus();
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailMatchesPatter(@NonNull TextView textView) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        if (!EMAIL_REGEX.matcher(textView.getText().toString()).matches()) {
            textView.setError( "يرجى كتابة الايميل بطريقة صحيحة");
            textView.requestFocus();
            return false;
        }
        return true;
    }
    public static boolean isSSnMatchesPatter(@NonNull TextView textView) {
        if (textView.getText().toString().length() != 10) {
            textView.setError("يجب ان يتكون الرقم الوطني من 10 ارقام");
            textView.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isPhoneMatchesPattern(@NonNull TextView textView) {
        String phone = textView.getText().toString();
        if (!(phone.charAt(0) == '0' && phone.charAt(1) == '7'
                && (phone.charAt(2) == '7' || phone.charAt(2) == '8' || phone.charAt(2) == '9'))) {
            textView.setError( "يرجى كتابة رقم الهاتف بالطريقة الصحيحة");
            textView.requestFocus();
            return false;
        }
        if (phone.length() != 10){
            textView.setError( "يجب ان يحتوي رقم الهاتف على 10 ارقام ");
            textView.requestFocus();
            return false;
        }

            return true;
        }

        public static boolean isSelected(TextView textView , @NonNull String str , String errorMessage){
        if (str.isEmpty()){
            textView.setError(errorMessage);
            textView.requestFocus();
            return false;
        }
        return true;
        }




}
