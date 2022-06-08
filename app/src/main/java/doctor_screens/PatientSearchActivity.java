package doctor_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class PatientSearchActivity extends AppCompatActivity {

    private int diseasesCount = 0 ;
    private int medicsCount= 0 ;
    private int xRayRecordsCount = 0 ;
    private int testCount = 0 ;
    private int reprotsCount = 0 ;

    private TextView txt_patient_name ;
    private TextView txt_patient_age ;
    private TextView txt_blood_type ;
    private TextView txt_patient_gender ;
    private TextView txt_x_count ;
    private TextView txt_report_count ;
    private TextView txt_test_count ;
    private TextView txt_medic_count ;
    private TextView txt_diseases_count ;

    String strBDate ;
    CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patien_search);
        txt_patient_name = findViewById(R.id.txt_patient_name);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_blood_type = findViewById(R.id.txt_blood_type);
        txt_patient_gender = findViewById(R.id.txt_patient_gender);
        txt_diseases_count = findViewById(R.id.txt_diseases_count);
        txt_patient_age = findViewById(R.id.txt_patient_age);
        txt_x_count = findViewById(R.id.txt_x_count);
        txt_test_count = findViewById(R.id.txt_test_count);
        txt_medic_count = findViewById(R.id.txt_medic_count);
        txt_report_count = findViewById(R.id.txt_report_count);

        String ssn = getIntent().getStringExtra("ssn");
        collectionReference = FirebaseFirestore.getInstance().collection("Users");
        final String[] documentId = {""};
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                      if(Objects.equals(document.getString("ssn"), ssn)){
                          strBDate = document.getString("birth_date");
                          txt_blood_type.setText(document.getString("blood_type"));
                          txt_patient_name.setText(document.getString("name"));
                          txt_patient_gender.setText(document.getString("gender"));
                          calculateAge(document.getString("birth_date"));
                          documentId[0] = document.getId();

                          CollectionReference diseaseRef = collectionReference
                                  .document(documentId[0])
                                  .collection("Diseases");

                          diseaseRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                              @Override
                              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                  if(task.isSuccessful()){
                                      for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                         diseasesCount ++ ;
                                         txt_diseases_count.setText(diseasesCount + "");
                                      }
                                  }
                              }
                          });
                          CollectionReference medicsRef = collectionReference
                                  .document(documentId[0])
                                  .collection("Medics");
                          medicsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                              @Override
                              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                  if(task.isSuccessful()){
                                      for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                          medicsCount ++ ;
                                          txt_medic_count.setText(diseasesCount + "");
                                      }
                                  }

                              }
                          });
                          CollectionReference recordsRef = collectionReference
                                  .document(documentId[0])
                                  .collection("Records");
                          recordsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                              @Override
                              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                  if(task.isSuccessful()){
                                      for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                          if(Objects.equals(document.getString("type"), "صورة اشعة")){
                                              xRayRecordsCount++;
                                              txt_x_count.setText(xRayRecordsCount);
                                          }else if(Objects.equals(document.getString("type"), "نتيجة فحص")){
                                              testCount ++ ;
                                              txt_test_count.setText(testCount + "");
                                          }else if(Objects.equals(document.getString("type"), "تقرير طبي")){
                                              reprotsCount++;
                                              txt_report_count.setText(reprotsCount);
                                          }
                                      }
                                  }
                              }
                          });



                      }
                    }

                } else {

                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void calculateAge(String birth_date) {

    }
}