package com.example.lab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view =loginBinding.getRoot();
        setContentView(view);

        loginBinding.btnLogin.setOnClickListener(this::fnLogin);
}

    private void fnLogin(View view) {

        String strLogin = loginBinding.edtUsername.getText().toString();
        String strPwd = loginBinding.edtPwd.getText().toString();

        Intent intentLogin = new Intent();
        if (strLogin.equals("lecturer@utem.edu.my")&& strPwd.equals("abc123"))
            intentLogin = new Intent (this, LecturerMainActivity.class);
        else if (strLogin.equals("student@utem.edu.my")&& strPwd.equals("123abc"))
            intentLogin = new Intent(this,StudentMainActivity.class);
        else
            Toast.makeText(this, "Sorry! Wrong username or password", Toast.LENGTH_SHORT).show();

        if (intentLogin != null)
        {
            intentLogin.putExtra("strLogin", strLogin);
            startActivityForResult(intentLogin, 0);
        }

//        String strTst = getString(R.string.tst_msg);
//        Toast.makeText(this, strTst, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String strMsg = data.getStringExtra("varLogout");
        String strDate = data.getStringExtra("varDate");
        String strTime = data.getStringExtra("varTime");
        loginBinding.txtVwOut.setText("Successfully logout! Message from next activity is " + strMsg
        + " at " + strDate + " " + strTime);

    }


}