package user_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import validation.Validation;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etEmail ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        etEmail = findViewById(R.id.etEmail);
    }

    public void reset_password_click(View view) {
        TextView [] textViews = {etEmail};

        if(!Validation.isEmpty(textViews) && Validation.isEmailMatchesPatter(etEmail)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.sendPasswordResetEmail(etEmail.getText().toString().trim())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ResetPasswordActivity.this, "تم ارسال رابط اعادة تعيين الى بريدك", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}