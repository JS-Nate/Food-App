package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.models.ModelOrder;

import java.time.LocalDate;
import com.example.foodapp.AppDatabase;

public class OrderPage extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int id;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 0);
        Log.d("Received in search page", "id ->" + id);

        db = new AppDatabase(this);
        ModelOrder order = db.getOrCreateOrder(id, 0, null, 0.0);


        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtonsFromOrder(id, this, homeButton, searchButton, orderButton, accountButton);


        Button submitOrder = findViewById(R.id.submitOrder);
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add to history of order table
                // clear current order stuff form database
            }
        });
    }
}