package com.example.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelUser;

public class ForgetPassword extends AppCompatActivity {
    EditText email, password, newPassword;
    TextView errorFN, errorLN, errorEM, errorPW;
    Button newPassButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        newPassword = findViewById(R.id.newpass);
        newPassButton = findViewById(R.id.submitPass);
        newPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
