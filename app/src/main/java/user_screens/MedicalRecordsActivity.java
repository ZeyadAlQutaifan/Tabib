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

    public void toShowRecords1(View view) {
        Intent intent = new Intent(getApplication() , ShowRecordActivity.class);
        intent.putExtra("type" , "تقرير طبي");
        startActivity(intent);
    }

    public void toShowRecords2(View view) {
        Intent intent = new Intent(getApplication() , ShowRecordActivity.class);
        intent.putExtra("type" , "صورة اشعة");
        startActivity(intent);
    }

    public void toShowRecords3(View view) {
        Intent intent = new Intent(getApplication() , ShowRecordActivity.class);
        intent.putExtra("type" , "نتيجة فحص");
        startActivity(intent);
    }
}