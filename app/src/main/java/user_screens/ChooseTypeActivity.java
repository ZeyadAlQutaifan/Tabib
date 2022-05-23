package user_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tabib.R;

import doctor_screens.SignUpDoctorActivity;

public class ChooseTypeActivity extends AppCompatActivity {

    private View userLine , doctorLine ;
    int type = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        userLine = findViewById(R.id.userLine);
        doctorLine = findViewById(R.id.doctorLine);

    }

    public void toRegisterUser(View view) {
        // مسؤول عن اظهار الخط الأخضر و اخفائه
        userLine.setVisibility(View.VISIBLE);
        doctorLine.setVisibility(View.GONE);


        type = 1 ;
    }

    public void toRegisterDoctor(View view) {
        type = 2 ;
        userLine.setVisibility(View.GONE);
        doctorLine.setVisibility(View.VISIBLE);
    }

    public void toRegister(View view) {

        if (type == 1){
            startActivity( new Intent(ChooseTypeActivity.this , SignUpUserActivity.class));
            
        }else if(type == 2){
            startActivity(new Intent(ChooseTypeActivity.this , SignUpDoctorActivity.class));

        }else {
            Toast.makeText(this, "يرجى الاختيار انشاء حساب ك طبيب أم مستخدم", Toast.LENGTH_SHORT).show();

        }
    }
}