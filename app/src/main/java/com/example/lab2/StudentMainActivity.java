package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.lab2.databinding.ActivityLecturerMainBinding;
import com.example.lab2.databinding.ActivityStudentMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StudentMainActivity extends AppCompatActivity {

    private EditText editTextDate;
    private EditText editTextTime;
    private Calendar calendar;
    private ActivityStudentMainBinding studentMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        studentMainBinding = ActivityStudentMainBinding.inflate(getLayoutInflater());
        View view = studentMainBinding.getRoot();
        setContentView(view);

        Intent intentFromLogin = getIntent();
        String strLogin = intentFromLogin.getStringExtra("strLogin");

        String strGreet = studentMainBinding.txtVwHelloLec.getText().toString();
        studentMainBinding.txtVwHelloLec.setText(strGreet + " " + strLogin);
        studentMainBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnLogout(view);
            }
        });
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        calendar = Calendar.getInstance();

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }
    public void fnLogout(View view)
    {
        String strLogoutMsg = studentMainBinding.edtLogout.getText().toString();
        String strDate = studentMainBinding.editTextDate.getText().toString();
        String strTime = studentMainBinding.editTextTime.getText().toString();
        Intent intentReturn = new Intent();
        intentReturn.putExtra("varLogout", strLogoutMsg);
        intentReturn.putExtra("varDate", strDate);
        intentReturn.putExtra("varTime", strTime);
        setResult(0, intentReturn);

        finish();
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateDateEditText();
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        updateTimeEditText();
                    }
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    private void updateDateEditText() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        editTextDate.setText(sdf.format(calendar.getTime()));
    }

    private void updateTimeEditText() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        editTextTime.setText(sdf.format(calendar.getTime()));
    }
}
