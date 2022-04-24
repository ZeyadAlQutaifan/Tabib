package screens;

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
        startActivity(new Intent(LoginActivity.this , SignUpActivity.class));
    }
}