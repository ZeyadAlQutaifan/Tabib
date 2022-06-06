package user_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import Modules.Medic;
import validation.Validation;

public class AddMedicActivity extends AppCompatActivity {

    EditText etMedicName ;
    EditText etMedicUses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medic);
        etMedicName = findViewById(R.id.etMedicName);
        etMedicUses = findViewById(R.id.etMedicUses);

    }

    public void uploadMedic(View view) {
        TextView [] textViews = {etMedicUses , etMedicName};
        if(!Validation.isEmpty(textViews)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = mFirestore.collection("Users")
                    .document(mAuth.getUid())
                    .collection("Medics")
                    .document();
            Medic medic = new Medic( Objects.requireNonNull(etMedicName.getText()).toString() , Objects.requireNonNull(etMedicUses.getText()).toString());
            documentReference.set(medic).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(AddMedicActivity.this, "تمت الاضافة بنجاح", Toast.LENGTH_SHORT).show();
               finish();

                }
            });
        }
    }
}