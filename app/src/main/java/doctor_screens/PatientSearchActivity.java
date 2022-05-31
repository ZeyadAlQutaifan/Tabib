package doctor_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;

public class PatientSearchActivity extends AppCompatActivity {

    TextView txt_patient_name ;
    TextView txt_patient_age ;
    TextView txt_blood_type ;
    TextView txt_patient_gender ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patien_search);
        txt_patient_name = findViewById(R.id.txt_patient_name);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_blood_type = findViewById(R.id.txt_blood_type);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_patient_gender = findViewById(R.id.txt_patient_gender);
        

    }
}