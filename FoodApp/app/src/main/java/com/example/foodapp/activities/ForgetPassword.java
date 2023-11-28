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

public class ForgetPassword extends AppCompatActivity {
    EditText email, password, newPassword;
    TextView errorMessage;
    ImageButton showPassword;
    boolean isPasswordVisible = false;

    Button newPassButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
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






        newPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppDatabase db = new AppDatabase(v.getContext());
//                int id = db.getUserIdFromEmail(email.getText().toString());

//                if (email.getText().toString() == null || newPassword.getText().toString() == null){
//                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
//                }
                errorMessage.setText("");



                AppDatabase db = new AppDatabase(v.getContext());
                    int id = db.getUserIdFromEmail(email.getText().toString());

                    if(id == -1){
//                        Toast.makeText(getApplicationContext(), "Sorry. That email does not exist for an account", Toast.LENGTH_SHORT).show();
                        errorMessage.setText("Sorry. That email does not exist for an account");
                    }
                    else if(newPassword.getText().toString().isEmpty()){
//                        Toast.makeText(getApplicationContext(), "Please enter your new password", Toast.LENGTH_SHORT).show();
                        errorMessage.setText("Please enter your new password");

                    }

                    else{
                        ModelUser oldUser = db.getUser(id);

                        ModelUser newUser = new ModelUser(
                                oldUser.getId(),
                                oldUser.getFirstName(),
                                oldUser.getLastName(),
                                oldUser.getEmail(),
                                newPassword.getText().toString(),
                                oldUser.getUserImage()
                        );

                        int rowsAffected = db.updateUser(id, newUser);
                        Intent intent = new Intent(ForgetPassword.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                    }

                }

        });
    }
}
