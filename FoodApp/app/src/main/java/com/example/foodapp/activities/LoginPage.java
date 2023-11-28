package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;

public class LoginPage extends AppCompatActivity {

    // email and password input
    EditText email, password;

    //submit and register buttons
    Button submitLogin, register;

    // to show/hide password
    ImageButton showPassword;
    boolean isPasswordVisible = false;

    // to display error message
    TextView errorMessage, forget;

    private AppDatabase db;

    void initializeDatabase() {
        // Seed database for testing purposes
        db = new AppDatabase(LoginPage.this);
        if (db.getTotalOrders() == 0) {
            db.seedDatabase();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initializeDatabase();

        // sets the values from the xml file
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submitLogin = findViewById(R.id.submitLogin);
        errorMessage = findViewById(R.id.errorMessage);
        showPassword = findViewById(R.id.showPassword);
        register = findViewById(R.id.register);
        forget = findViewById(R.id.btn_forget);


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
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, ForgetPassword.class);
                startActivity(intent);
                finish();
            }
        });

        // to submit the user's login info
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean exists = db.userExists(email.getText().toString(), password.getText().toString());
                boolean emailEmpty = email.getText().toString().trim().isEmpty();
                boolean passwordEmpty = password.getText().toString().trim().isEmpty();

                /* If the credentials exist in the database */
                // and makes sure the email or password input isnt empty
                if(exists && !emailEmpty && !passwordEmpty){
                    int userId = db.getUserId(email.getText().toString(), password.getText().toString());

                    if (userId != -1) {
                        //Toast.makeText(getApplicationContext(), "user exists in database", Toast.LENGTH_SHORT).show();
                        // Intent to enter the Home Page
                        Intent intent = new Intent(LoginPage.this, HomePage.class);
                        intent.putExtra("userId", userId);
                        Log.d("Sent", "id = " + userId);
                        startActivity(intent);
//                        db.seedDatabase();
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
                finish();
            }
        });

    }
}