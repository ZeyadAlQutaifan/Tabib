package doctor_screens;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabib.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

import Modules.Doctor;
import Modules.Patient;

public class DoctorOTPActivity extends AppCompatActivity {
    TextView otp;
    EditText otp_box_1,otp_box_2,otp_box_3,otp_box_4,otp_box_5,otp_box_6;
    Button btnVerify;
    private String strName;
    private String strPhoneNumber;
    private String strEmail;
    private String strPassword;
    private String strSSN;
    private String strGender;
   private String strSpecialization;
   private String strImageUri ;

    Doctor doctor;
    FirebaseAuth mAuth;
    PhoneAuthCredential phoneAuthCredential;
    PhoneAuthProvider.ForceResendingToken token;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_doctor);
        mAuth = FirebaseAuth.getInstance();
        btnVerify = findViewById(R.id.verify);
        otp = findViewById(R.id.otp);
        otp_box_1 = findViewById(R.id.otp_box_1);
        otp_box_2 = findViewById(R.id.otp_box_2);
        otp_box_3 = findViewById(R.id.otp_box_3);
        otp_box_4 = findViewById(R.id.otp_box_4);
        otp_box_5 = findViewById(R.id.otp_box_5);
        otp_box_6 = findViewById(R.id.otp_box_6);
        strPhoneNumber = getIntent().getExtras().getString("phone");
        strEmail = getIntent().getExtras().getString("email");
        strPassword = getIntent().getExtras().getString("password");
        strSSN = getIntent().getExtras().getString("ssn");
        strPhoneNumber = getIntent().getExtras().getString("phone");
        strGender = getIntent().getExtras().getString("gender");
        strName = getIntent().getExtras().getString("name");
        strSpecialization = getIntent().getExtras().getString("specialization");
        strImageUri = getIntent().getExtras().getString("uri");
        doctor = new Doctor();
        doctor.setName(strName);
        doctor.setEmail(strEmail);
        doctor.setSsn(strSSN);
        doctor.setPhone(strPhoneNumber);
        doctor.setGender(strGender);
        doctor.setSpecialization(strSpecialization);
        doctor.setLicense_image(strImageUri);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String OTP = otp_box_1.getText().toString()
                        +  otp_box_2.getText().toString()
                        +  otp_box_3.getText().toString()
                        +  otp_box_4.getText().toString()
                        +  otp_box_5.getText().toString()
                        +  otp_box_6.getText().toString() ;


                try{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, OTP);
                    verifyAuthentication(credential);
                }catch (Exception e){
                    Toast.makeText(DoctorOTPActivity.this, "???????? ?????????? ?????? ??????????????", Toast.LENGTH_LONG).show();
                }

            }
        });
        Toast.makeText(this, strPhoneNumber, Toast.LENGTH_SHORT).show();



        otp.setText(Html.fromHtml(getResources().getString(R.string.otp1)));
        otp_box_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_2.requestFocus();
                }
            }
        });
        otp_box_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_3.requestFocus();
                }
            }
        });
        otp_box_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_4.requestFocus();
                }
            }
        });
        otp_box_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_5.requestFocus();
                }
            }
        });
        otp_box_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_6.requestFocus();
                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                verifyAuthentication(phoneAuthCredential);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);



            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                token = forceResendingToken;
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.v("test112", e.getMessage());
                Toast.makeText(DoctorOTPActivity.this, "?????? ???? ??????????????", Toast.LENGTH_LONG).show();
            }
        };

        sendOTPCode("+962"+strPhoneNumber.substring(1));

    }

    private void sendOTPCode(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                DoctorOTPActivity.this,
                mCallbacks
        );
    }


    private void verifyAuthentication(PhoneAuthCredential credential) {

        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                mAuth.getCurrentUser().linkWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(DoctorOTPActivity.this, "???? ?????????????? ??????????", Toast.LENGTH_LONG).show();
                        addUserToFirestorage(mAuth.getUid());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        deleted(strEmail , strPassword);
                        Button button = new Button(DoctorOTPActivity.this);
                        button.setText("??????????");
                        button.setOnClickListener(v -> {
                            finish();

                        });

                    }
                });
            }
        });


    }
    private void addUserToFirestorage(String uid) {
        String userId = mAuth.getCurrentUser().getUid();
        String userEmail = mAuth.getCurrentUser().getEmail();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("License").child(userEmail).child(strImageUri);
        storageReference.putFile(Uri.parse(strImageUri.toString()))
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            //throw task.getException();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri uri = task.getResult();
                doctor.setLicense_image(uri.toString());
                addUserToFirestore(userId);
            }
        });

    }

    private void deleted(String email, String password) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        // Prompt the user to re-provide their sign-in credentials
        if (user != null) {
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                deleteFromFirestore(uid);

                                            }
                                        }
                                    });
                        }
                    });
        }
    }
    private void deleteFromFirestore(String uid) {
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Users").document(uid);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
    private void addUserToFirestore(String uid){
        DocumentReference mFirestore = FirebaseFirestore.getInstance().collection("Doctors").document(uid);
        mFirestore.set(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(DoctorOTPActivity.this , DoctorHomeActivity.class));
                finish();
            }
        });
    }
}