package user_screens;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tabib.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import validation.Validation;


public class SignUpUserActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    private TextInputEditText etName ;
    private TextInputEditText etEmail ;
    private TextInputEditText etPhone ;
    private TextInputEditText etPassword ;
    private TextInputEditText etSSN ;
private AutoCompleteTextView autocompleteTV ;
private AutoCompleteTextView autocompleteTV1 ;


    private String strName;
    private String strPhoneNumber;
    private String strEmail;
    private String strPassword;
    private String strSSN;
    private String strGender;
    private String strBirthDate;
    private String strBloodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etSSN = findViewById(R.id.etSSN);

        String[] bloods = getResources().getStringArray(R.array.blood_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_blood, bloods);
       autocompleteTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);
        autocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String [] arr = getResources().getStringArray(R.array.blood_array);
                strBloodType = arr[i];
            }
        });


        String[] genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.item_blood, genders);
        autocompleteTV1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        autocompleteTV1.setAdapter(arrayAdapter1);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressWarnings("deprecation")
    public void pickBDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();


    }



    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        StringBuilder strDate = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);

        System.out.println(strDate);
    }


    public void toChooseType(View view) {

    }

    public void toOTP(View view) {

        TextView[] textViews = {
                etName , etEmail , etPhone , etPassword , etSSN
        };


      if( !Validation.isEmpty(textViews , "لا يمكن ان يكون فارغاً"))
      {
          if(Validation.isEmailMatchesPatter(etEmail , "يرجى كتابة الايميل بطريقة صحيحة")
          && Validation.isSSnMatchesPatter(etSSN , "يرجة كتابة الرقم الوطني بطريقة صحيحة")
          && Validation.isPhoneMatchesPattern(etPhone , "يرجى كتابة رقم الهاتف بالطريقة الصحيحة")
          ){
              System.out.println("success!");
          }

      }



        Toast.makeText(this, strBloodType, Toast.LENGTH_LONG).show();
    }
}