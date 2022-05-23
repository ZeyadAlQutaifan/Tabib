package user_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tabib.R;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail ;
    EditText etPassword ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void toSignUp(View view) {
        startActivity(new Intent(LoginActivity.this , SignUpUserActivity.class));
    }

    public void toHome(View view) {
        startActivity(new Intent(LoginActivity.this , HomeActivity.class));
    }

    public void toResetPassword(View view) {
       startActivity(new Intent(LoginActivity.this , ResetPasswordActivity.class));
    }

    public void toChooseType(View view) {
        startActivity(new Intent(LoginActivity.this , ChooseTypeActivity.class));
    }
}