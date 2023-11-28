package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelUser;

public class RegisterPage extends AppCompatActivity {

    // initializing input, error-text and button views from xml
    EditText firstName, lastName, email, password;
    Button submitButton;
    TextView errorFN, errorLN, errorEM, errorPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // sets value of input and button views from xml
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submitButton = findViewById(R.id.registerButton);

        // to display error messages of the user not entering the correct credential inputs
        errorFN = findViewById(R.id.errorFN);
        errorLN = findViewById(R.id.errorLN);
        errorEM = findViewById(R.id.errorEM);
        errorPW = findViewById(R.id.errorPW);


        // when the user submits their registration credentials
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // removes pre-existing error message to replace in case user enters a valid input
                errorFN.setText("");
                errorLN.setText("");
                errorEM.setText("");
                errorPW.setText("");

                // to check if the user enters a valid input for each
                boolean checkFN = firstName.getText().toString().trim().isEmpty();
                boolean checkLN = lastName.getText().toString().trim().isEmpty();
                boolean checkEM = email.getText().toString().trim().isEmpty() || !email.getText().toString().trim().contains("@");
                boolean checkPW = password.getText().toString().trim().isEmpty();

                // adds new user to database if inputs are valid
                if(!checkFN && !checkLN && !checkEM && !checkPW){
                    Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_SHORT).show();

                    // sets a new user's default avatar
                    int resourceId = R.drawable.default_avatar;
                    String userImage = String.valueOf(resourceId);

                    ModelUser modelUser = new ModelUser(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            userImage
                    );
                    AppDatabase db = new AppDatabase(RegisterPage.this);
                    db.addUser(modelUser);

                    // and returns to login page
                    Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                }

                // displays error message of each invalid input
                else{
                    if(checkFN){
                        errorFN.setText("Invalid first name input");
                    }
                    if(checkLN){
                        errorLN.setText("Invalid last name input");
                    }
                    if(checkEM){
                        errorEM.setText("Invalid email input");
                    }
                    if(checkPW){
                        errorPW.setText("Invalid password input");
                    }
                }

            }
        });



    }
}