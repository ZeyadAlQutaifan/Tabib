package user_screens;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import Modules.Disease;
import validation.Validation;

public class AddDiseaseActivity extends AppCompatActivity {

    TextInputEditText etDiseaseName ;

    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_disease);
        etDiseaseName = findViewById(R.id.etMedicName);

    mAuth = FirebaseAuth.getInstance();

    }

    public void addDisease(View view) {
        TextView [] textViews = {
                etDiseaseName
        };
        if(!Validation.isEmpty(textViews)){
            Disease disease = new Disease();
            disease.setName(Objects.requireNonNull(etDiseaseName.getText()).toString());

            FirebaseFirestore  firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection("Users")
                    .document(mAuth.getUid()).collection("Diseases").document();
            documentReference.set(disease).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddDiseaseActivity.this, "تمت الاضافة بنجاح", Toast.LENGTH_SHORT).show();
                finish();

                }
            });

        }
    }
}