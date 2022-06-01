package doctor_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientSearchActivity extends AppCompatActivity {

    TextView txt_patient_name ;
    TextView txt_patient_age ;
    TextView txt_blood_type ;
    TextView txt_patient_gender ;
    String strBDate ;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patien_search);
        txt_patient_name = findViewById(R.id.txt_patient_name);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_blood_type = findViewById(R.id.txt_blood_type);
        txt_patient_gender = findViewById(R.id.txt_patient_gender);
        String ssn = getIntent().getStringExtra("ssn");
        documentReference = FirebaseFirestore.getInstance().collection("Users").document(ssn);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()){
                        strBDate = documentSnapshot.getString("birth_date");
                        txt_blood_type.setText(documentSnapshot.getString("blood_type"));
                        txt_patient_name.setText(documentSnapshot.getString("name"));
                        txt_patient_gender.setText(documentSnapshot.getString("gender"));
                        calculateAge(documentSnapshot.getString("birth_date")); 
                    }
                }
            }
        });

    }

    private void calculateAge(String birth_date) {

    }
}