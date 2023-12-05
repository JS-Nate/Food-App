package com.example.foodapp.activities;
/* This page lets the user reset their password to a new one */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelUser;

public class ChangeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        // on screen elements
        TextView oldPassword = findViewById(R.id.password);
        TextView newPassword = findViewById(R.id.newpass);
        Button submitPass = findViewById(R.id.submitPass);

        // gets the user id from the intent
        Intent intent = getIntent();
        int userID = intent.getIntExtra("UserID", 0);


        // when the user submits their new password
        submitPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gets current user info from the database
                AppDatabase db  = new AppDatabase(ChangeInfo.this);
                ModelUser thisUser = db.getUser(userID);

                // checks if they entered the incorrect old password of theirs
                if (!oldPassword.getText().toString().equals(thisUser.getPassword())){
                    Toast.makeText(getApplicationContext(), "Incorrect Old Password", Toast.LENGTH_SHORT).show();
                }

                // checks if they entered a blank new password
                else if(newPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter a New Password", Toast.LENGTH_SHORT).show();
                }

                // they entered the correct old password and a correct new one
                else{
                    // updates this user in the database with their new password
                    String newPass = newPassword.getText().toString();
                    ModelUser updatedUser = new ModelUser(
                            userID,
                            thisUser.getFirstName(),
                            thisUser.getLastName(),
                            thisUser.getEmail(),
                            newPass,
                            thisUser.getUserImage()
                    );
                    int rowsAffected = db.updateUser(userID, updatedUser);

                    // returns to the home page
                    Intent intent = new Intent(ChangeInfo.this, HomePage.class);
                    intent.putExtra("userId", userID);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}