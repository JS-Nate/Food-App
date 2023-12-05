package com.example.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelUser;
/* This page allows a user to rest their password to a new one incase they forgot their old one */
public class ForgetPassword extends AppCompatActivity {
    // on screen views, buttons and inputs
    EditText email, password, newPassword;
    TextView errorMessage;
    ImageButton showPassword;
    boolean isPasswordVisible = false;
    Button newPassButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        // those same on screen views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        newPassword = findViewById(R.id.newpass);
        showPassword = findViewById(R.id.showPassword);
        errorMessage = findViewById(R.id.errorMessage);
        newPassButton = findViewById(R.id.submitPass);




        // changes the password visibility upon clicking the showPassword button
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordVisible){
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.visibility_off_24px);
                } else{
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    showPassword.setImageResource(R.drawable.visibility_24px);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });





        // when the user submits their new password
        newPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to display the specified error message on screen
                errorMessage.setText("");

                // database reference
                AppDatabase db = new AppDatabase(v.getContext());
                // attempts to find the suer based on tier email
                int id = db.getUserIdFromEmail(email.getText().toString());

                // displays error message if the user email is not registered
                if(id == -1){
                    errorMessage.setText("Sorry. That email does not exist for an account");
                }

                // displays error message if the new password is empty
                else if(newPassword.getText().toString().isEmpty()){
                    errorMessage.setText("Please enter your new password");

                }

                else{
                    // creates a model user to update the old one with the new password
                    ModelUser oldUser = db.getUser(id);
                    ModelUser newUser = new ModelUser(
                            oldUser.getId(),
                            oldUser.getFirstName(),
                            oldUser.getLastName(),
                            oldUser.getEmail(),
                            newPassword.getText().toString(),
                            oldUser.getUserImage()
                    );
                    // updates the database with the updated user values(password)
                    int rowsAffected = db.updateUser(id, newUser);

                    // returns to the login page
                    Intent intent = new Intent(ForgetPassword.this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
