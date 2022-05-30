package user_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabib.R;

public class DiseasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disdeases);
    }

    public void toAddDisease(View view) {
        startActivity(new Intent(getApplication() , AddDiseaseActivity.class));
    }
}