package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;

public class OrderHistory extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtonsFromOrder(id, this, homeButton, searchButton, orderButton, accountButton);




    }
}