package user_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.tabib.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddRecordActivity extends AppCompatActivity {
ImageView img_record_image ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        String[] records_types_array = getResources().getStringArray(R.array.records_types_array) ;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_blood, records_types_array);
        AutoCompleteTextView autocompleteTV =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);


        img_record_image = findViewById(R.id.img_record_image);

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
    }
}