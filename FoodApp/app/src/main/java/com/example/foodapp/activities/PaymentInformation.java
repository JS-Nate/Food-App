package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.foodapp.ToolbarHandler;


public class PaymentInformation extends AppCompatActivity {

    // toolbar buttons
    ImageButton homeButton, searchButton, orderButton, accountButton;

    // user and order id
    int userID;
    int orderID;

    // database
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        // database initialization
        db = new AppDatabase(this);

        // gets the user and order id from the intent
        Intent intent = getIntent();
        userID = intent.getIntExtra("userId", 0);
        orderID = intent.getIntExtra("orderId", 0);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtons(userID, this, homeButton, searchButton, orderButton, accountButton);

        Button submitPayment = findViewById(R.id.submitPayment);

        // submits the order and changes its status to submitted
        // then returns to the home page
        submitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.setOrderStatus(orderID,"Submitted");
                Toast.makeText(getApplicationContext(), "Your order has been submitted!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentInformation.this, HomePage.class);
                intent.putExtra("userId", userID);
                startActivity(intent);
            }
        });
    }
}