package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.Intent;

public class LoginPage extends AppCompatActivity {

    // email and password input
    EditText email, password;

    //submit and register buttons
    Button submitLogin, register;

    // to show/hide password
    ImageButton showPassword;
    boolean isPasswordVisible = false;

    // to display error message
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // sets the values from the xml file
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submitLogin = findViewById(R.id.submitLogin);
        errorMessage = findViewById(R.id.errorMessage);
        showPassword = findViewById(R.id.showPassword);
        register = findViewById(R.id.register);


        // temporary prefilled login to make testing easier
        email.setText("student.smith@email.com");
        password.setText("Password123");


        // changes the password visibility upon clicking the showPassword button
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPasswordVisible){
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.visibility_off_24px);
                } else{
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    showPassword.setImageResource(R.drawable.visibility_24px);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });


        // to submit the user's login info
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDatabase db = new LoginDatabase(LoginPage.this);
                boolean exists = db.userExists(email.getText().toString(), password.getText().toString());
                boolean emailEmpty = email.getText().toString().trim().isEmpty();
                boolean passwordEmpty = password.getText().toString().trim().isEmpty();

                /* If the credentials exist in the database */
                // and makes sure the email or password input isnt empty
                if(exists && !emailEmpty && !passwordEmpty){
                    int userId = db.getUserId(email.getText().toString(), password.getText().toString());

                    if (userId != -1) {
                        Toast.makeText(getApplicationContext(), "user exists in database", Toast.LENGTH_SHORT).show();
                        // Intent to enter the Home Page
                        Intent intent = new Intent(LoginPage.this, HomePage.class);
                        intent.putExtra("userId", userId);
                        Log.d("Sent", "id = " + userId);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error: User ID not found", Toast.LENGTH_SHORT).show();
                    }

                }

                /* If not, informs user of incorrect input(s) */
                else{
                    errorMessage.setText("Incorrect Email or Password");
                }

            }
        });


        // to register if they dont have an account in the database
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent);
            }
        });

    }
}