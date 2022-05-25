package doctor_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabib.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import user_screens.SignUpUserActivity;
import user_screens.UserOTPActivity;
import validation.Validation;

public class SignUpDoctorActivity extends AppCompatActivity {
    ImageView img_license_image ;
    Uri imageUri;
    private TextInputEditText etName ;
    private TextInputEditText etEmail ;
    private TextInputEditText etPhone ;
    private TextInputEditText etPassword ;
    private TextInputEditText etSSN ;
    private TextInputEditText etSpecialization ;

    private AutoCompleteTextView autocompleteTV1 ;
    private AutoCompleteTextView autocompleteTV2 ;
    private String strGender;
    private String strCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etSSN = findViewById(R.id.etSSN);
        etSpecialization = findViewById(R.id.etSpecialization);

        img_license_image = findViewById(R.id.img_license_image);
        String[] genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.item_blood, genders);
        autocompleteTV1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autocompleteTV1.setAdapter(arrayAdapter1);
        autocompleteTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] arr = getResources().getStringArray(R.array.blood_array);
                strGender = arr[i];
            }
        });

        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.item_blood, cities );
        autocompleteTV2 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autocompleteTV2.setAdapter(arrayAdapter2);
        autocompleteTV2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] arr = getResources().getStringArray(R.array.cities);
                strCity = arr[i];
            }
        });
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
        TextView[] textViews = {
                etName , etEmail , etPhone , etPassword , etSSN , etSpecialization
        };


        if( !Validation.isEmpty(textViews ))
        {
            if(Validation.isEmailMatchesPatter(etEmail)
                    && Validation.isSSnMatchesPatter(etSSN)
                    && Validation.isPhoneMatchesPattern(etPhone)
            ) {
                if (Validation.isSelected(autocompleteTV1, strGender, "يجب اختيار الجنس")
                        && Validation.isSelected(autocompleteTV2, strCity, "يجب تحديد المدينة")
                ) {

                    Bundle bundle = new Bundle() ;
                    bundle.putString("name" , Objects.requireNonNull(etName.getText()).toString() );
                    bundle.putString("email" , Objects.requireNonNull(etEmail.getText()).toString());
                    bundle.putString("password" , Objects.requireNonNull(etPassword.getText()).toString());
                    bundle.putString("gender" , strGender);
                    bundle.putString("specialization" , Objects.requireNonNull(etSpecialization.getText()).toString());
                    bundle.putString("phone" , Objects.requireNonNull(etPhone.getText()).toString());
                    bundle.putString("ssn" , Objects.requireNonNull(etSSN.getText()).toString());
                    bundle.putString("city" , strCity);
                    Intent intent = new Intent(SignUpDoctorActivity.this , UserOTPActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }

            }

        }
    }
}