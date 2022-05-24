package doctor_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tabib.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class SignUpDoctorActivity extends AppCompatActivity {
    ImageView img_license_image ;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);

        img_license_image = findViewById(R.id.img_license_image);

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
        imageUri = imageReturnedIntent.getData();
        img_license_image.setImageURI(imageUri);
    }

    public void toOTP(View view) {


    }
}