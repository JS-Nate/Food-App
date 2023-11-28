package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelOrder;
import java.time.LocalDate;


public class ItemDetails extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int userID;
    int itemID;
    double sizePrice = 0;
    int vendorID;
    AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();

        userID = intent.getIntExtra("userID", 0);
        itemID = intent.getIntExtra("item_id", 0);
        Log.d("Received in item page", "userID ->" + userID + "itemID ->" + itemID);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtons(userID, this, homeButton, searchButton, orderButton, accountButton);

        db = new AppDatabase(this);
        ModelMenuItem thisMenuItem = db.getMenuItem(itemID);
        String itemName = thisMenuItem.getItemName();
        String itemDescription = thisMenuItem.getItemDescription();
        String itemImage = thisMenuItem.getItemImage();
        vendorID = thisMenuItem.getVendorID();
        double itemPrice = Double.valueOf(thisMenuItem.getItemPrice());


        // the item data from the database
        TextView showName = findViewById(R.id.showName);
        TextView showDescription = findViewById(R.id.showDescription);
        TextView showPrice = findViewById(R.id.showPrice);

        // Get the item details from the intent or another source
        showName.setText(itemName);
        showDescription.setText(itemDescription);
        showPrice.setText(String.valueOf(itemPrice));


        /* FOR THE USER INPUT*/

        // for the user to choose what size item
        RadioGroup radioGroup = findViewById(R.id.sizeChoice);
        RadioButton small = findViewById(R.id.small);
                    small.setChecked(true);
        RadioButton medium = findViewById(R.id.medium);
        RadioButton large = findViewById(R.id.large);
        TextView specialInstructions;

        // for the user to choose the size of the item
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was selected and assigns the added price to the size
                if (checkedId == R.id.small) {
                    sizePrice = 0.25;
                } else if (checkedId == R.id.medium) {
                    sizePrice = 0.50;
                } else if (checkedId == R.id.large) {
                    sizePrice = 0.75;
                }

            }
        });

        // for the user to enter their amount
        EditText showAmount = findViewById(R.id.showAmount);
        Button addToOrder = findViewById(R.id.addToOrder);

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            //    tax * (itemquantity  * (default item price + size price))
                double subTotal = 1.13*(Double.valueOf(showAmount.getText().toString())*(itemPrice + sizePrice));
                Toast.makeText(getApplicationContext(), "Adding to Order", Toast.LENGTH_SHORT).show();

                // check if an open order exists
                ModelOrder order = db.getOrCreateOrder(userID, vendorID, LocalDate.now().toString(), subTotal);

            }
        });



    }
}
