package user_screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tabib.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Modules.Record;

public class AddRecordActivity extends AppCompatActivity {
    ImageView img_record_image;
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


        mAuth = FirebaseAuth.getInstance();
        String[] records_types_array = getResources().getStringArray(R.array.records_types_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_blood, records_types_array);
        AutoCompleteTextView autocompleteTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);
        autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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

    }

    void uploadRecordToFirestore() {
        DocumentReference firebaseFirestore = FirebaseFirestore.getInstance()
                .collection("Users")
                .document(mAuth.getUid())
                .collection("Records").document();
        record = new Record();
        record.setAdditional_notes(strAdditionalNotes);
        record.setType(strRecordType);

    }
}