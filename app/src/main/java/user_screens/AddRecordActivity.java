package user_screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tabib.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Modules.Record;
import validation.Validation;

public class AddRecordActivity extends AppCompatActivity {
    ImageView img_record_image;
    TextInputEditText etAdditionalNotes ;
    private String strRecordType;
    private String strAdditionalNotes;
    private String strImageUri;
    private Record record;
    StorageReference mStorage;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        etAdditionalNotes = findViewById(R.id.etDiseaseName);

        mAuth = FirebaseAuth.getInstance();
        String[] records_types_array = getResources().getStringArray(R.array.records_types_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_blood, records_types_array);
        AutoCompleteTextView autocompleteTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);
        autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] types = getResources().getStringArray(R.array.records_types_array);
                strRecordType = types[i];
            }
        });

        img_record_image = findViewById(R.id.img_license_image);

    }

    public void pickImage(View view) {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri imageUri = imageReturnedIntent.getData();
        img_record_image.setImageURI(imageUri);
        strImageUri = imageUri.toString();
    }

    void uploadImageToFirestorage() {
        String userId = mAuth.getCurrentUser().getUid();
        String userEmail = mAuth.getCurrentUser().getEmail();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Records").child(userEmail).child(strImageUri);
        storageReference.putFile(Uri.parse(strImageUri.toString()))
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                Uri uri = task.getResult();
                record.setImageUri(uri.toString());
                uploadRecordToFirestore();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void uploadRecordToFirestore() {
        DocumentReference firebaseFirestore = FirebaseFirestore.getInstance()
                .collection("Users")
                .document(mAuth.getUid())
                .collection("Records").document();
        record = new Record();
        record.setAdditional_notes(strAdditionalNotes);
        record.setType(strRecordType);
        firebaseFirestore.set(record).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
            }
        });

    }

    public void addRecord(View view) {
        TextView [] textViews = {etAdditionalNotes};
        if(!Validation.isEmpty(textViews) && !strImageUri.isEmpty() && !strRecordType.isEmpty()){
            uploadImageToFirestorage();
        }

    }
}