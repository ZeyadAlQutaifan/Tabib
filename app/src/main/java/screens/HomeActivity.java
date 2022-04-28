package screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabib.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void toMedicalRecords(View view) {
        startActivity(new Intent(HomeActivity.this , MedicalRecordsActivity.class));
    }

    public void toMedicalDrugs(View view) {
        startActivity(new Intent(HomeActivity.this , MedicalDrugsActivity.class));
    }

    public void toIllnesses(View view) {
        startActivity(new Intent(HomeActivity.this , IllnessesActivity.class));
    }

    public void toDoctors(View view) {
        startActivity(new Intent(HomeActivity.this , DoctorsActivity.class));
    }

    public void toMedicalAdvice(View view) {
        startActivity(new Intent(HomeActivity.this , MedicalAdvicesActivity.class));
    }
}