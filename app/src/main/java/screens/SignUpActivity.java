package screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;

import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.tabib.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;


public class SignUpActivity extends AppCompatActivity {
private  String strBirthDate ;
private TextInputLayout textInputLayout;
private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
    }

    public void pickBDate(View view) {

        showDateTimePicker();

    }

  /*  @Override
    protected void onStart() {
        super.onStart();
        textInputLayout = findViewById(R.id.menu_drop);
        autoCompleteTextView = findViewById(R.id.drop_items);
        String[] rates = {
              "O+" , "O-" , "A+" , "A-" , "B+" , "B-" , "AB+" , "AB-"
        };
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, R.layout.item_blod, rates);
        autoCompleteTextView.setAdapter(itemAdapter);

    }
*/
    Calendar date;
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date.set(2022, 1, 10);
                new TimePickerDialog(SignUpActivity.this , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        strBirthDate =    date.getTime().toString();
                        System.out.println(strBirthDate);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }


        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
}