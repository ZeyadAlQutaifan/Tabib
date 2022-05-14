package user_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabib.R;

public class MedicalRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);
    }

    public void toAddRecord(View view) {
        startActivity(new Intent(MedicalRecordsActivity.this , AddRecordActivity.class));
    }
}