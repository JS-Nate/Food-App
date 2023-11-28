package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodapp.R;

import android.util.Log;
import android.widget.ImageButton;
import com.example.foodapp.ToolbarHandler;


public class PaymentInformation extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        Intent intent = getIntent();
        userID = intent.getIntExtra("userId", 0);
        Log.d("Received in search page", "id ->" + userID);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtons(userID, this, homeButton, searchButton, orderButton, accountButton);
    }
}