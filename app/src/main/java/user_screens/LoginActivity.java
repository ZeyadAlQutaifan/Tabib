package user_screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabib.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

import doctor_screens.DoctorHomeActivity;
import validation.Validation;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail ;
    EditText etPassword ;
    String strEmail ;
    String strPassword;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance() ;
        mFirestore = FirebaseFirestore.getInstance();

        //TODO write a code to check if the user is already logged in


    }




    public void toHome(View view) {
        TextView[] textViews = {
                etEmail ,
                etPassword
        } ;
        if(!Validation.isEmpty(textViews ) && Validation.isEmailMatchesPatter(etEmail)){
            strEmail = etEmail.getText().toString().trim();
            strPassword = etPassword.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(strEmail , strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    checkType(mAuth.getUid());
                }
            });

        }

    }

    public void toResetPassword(View view) {
       startActivity(new Intent(LoginActivity.this , ResetPasswordActivity.class));
    }

    public void toChooseType(View view) {
        startActivity(new Intent(LoginActivity.this , ChooseTypeActivity.class));
    }


    void checkType(String id){
        FirebaseFirestore userRootRef = FirebaseFirestore.getInstance();
        DocumentReference studentDocIdRef = userRootRef.collection("Users").document(id);
        studentDocIdRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                if (documentSnapshot.exists()) {


                    userLogin();


                }

            }
        });

        FirebaseFirestore doctorRootRef = FirebaseFirestore.getInstance();
        DocumentReference brokerDocIdRef = doctorRootRef.collection("Doctors").document(id);
        brokerDocIdRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {

                    doctorLogin();
                }

            }
        });
    }

    void userLogin(){
        startActivity(new Intent(getApplication() , HomeActivity.class));
    }
    void doctorLogin(){
        startActivity(new Intent(getApplication(), DoctorHomeActivity.class));
    }

}