package screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tabib.R;


import java.util.Calendar;


public class SignUpActivity extends AppCompatActivity {
    private String strBirthDate;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String[] languages = getResources().getStringArray(R.array.blood_array) ;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_blood, languages);
        AutoCompleteTextView autocompleteTV =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autocompleteTV.setAdapter(arrayAdapter);

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
      //  showDate(year, month+1, day);
     //   showDateTimePicker();

    }


    //Calendar date;

//    public void showDateTimePicker() {
//        final Calendar currentDate = Calendar.getInstance();
//        date = Calendar.getInstance();
//        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                date.set(2022, 1, 10);
//                strBirthDate = date.getTime().toString();
//                System.out.println(strBirthDate);
//                /*
//                new TimePickerDialog(SignUpActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        date.set(Calendar.MINUTE, minute);
//
//
//                    }
//                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();*/
//
//            }
//
//
//        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
//    }



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


}