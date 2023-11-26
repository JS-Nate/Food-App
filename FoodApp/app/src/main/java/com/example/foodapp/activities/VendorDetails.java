package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.models.ModelVendor;
import com.google.android.material.tabs.TabLayout;

public class VendorDetails extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;

    int id;
    TextView name;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);


        AppDatabase db = new AppDatabase(this);
        Intent intent = getIntent();


        id = intent.getIntExtra("vendorID", 0);
        int userID = intent.getIntExtra("userID", 0);
        ModelVendor modelVendor = db.getVendorFromId(id);

        Log.d("Received in vendor page", "id ->" + userID);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtons(userID, this, homeButton, searchButton, orderButton, accountButton);


        // page views
        name = findViewById(R.id.restaurantTitle);
        tabLayout = findViewById(R.id.tabLayout);






        name.setText(modelVendor.getName().toString().toString());





    /*
    * recycler view
    * only show item by tag
    *
    *
    */




    }
}


