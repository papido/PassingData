package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab2.databinding.ActivityLecturerMainBinding;

public class LecturerMainActivity extends AppCompatActivity {

    private ActivityLecturerMainBinding lecturerMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lecturerMainBinding = ActivityLecturerMainBinding.inflate(getLayoutInflater());
        View view = lecturerMainBinding.getRoot();
        setContentView(view);

        Intent intentFromLogin = getIntent();
        String strLogin = intentFromLogin.getStringExtra("strLogin");

        String strGreet = lecturerMainBinding.txtVwHelloLec.getText().toString();
        lecturerMainBinding.txtVwHelloLec.setText(strGreet + " " + strLogin);
        lecturerMainBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnLogout(view);
            }
        });
    }
    public void fnLogout(View view)
    {
        String strLogoutMsg = lecturerMainBinding.edtLogout.getText().toString();
        Intent intentReturn = new Intent();
        intentReturn.putExtra("varLogout", strLogoutMsg);
        setResult(0, intentReturn);

        finish();
    }
}