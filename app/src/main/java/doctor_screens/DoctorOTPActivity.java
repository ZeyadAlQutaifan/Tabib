package doctor_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tabib.R;

public class DoctorOTPActivity extends AppCompatActivity {
    TextView otp;
    EditText otp_box_1,otp_box_2,otp_box_3,otp_box_4,otp_box_5,otp_box_6;
    Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_doctor);
    }

    public void register_doctor_click(View view) {
    }
}