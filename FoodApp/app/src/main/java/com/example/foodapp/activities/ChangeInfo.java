package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.R;

public class ChangeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        TextView oldPassword = findViewById(R.id.password);
        TextView newPassword = findViewById(R.id.newpass);
        Button submitPass = findViewById(R.id.submitPass);

        Intent intent = getIntent();
        int userID = intent.getIntExtra("UserID", 0);



        submitPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeInfo.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });








    }
}