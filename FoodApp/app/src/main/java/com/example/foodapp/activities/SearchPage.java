package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;

public class SearchPage extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 0);
        Log.d("Received in search page", "id ->" + id);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtonsFromSearch(id, this, homeButton, searchButton, orderButton, accountButton);


//        SearchView searchBar = findViewById(R.id.searchBar);
        RecyclerView searchRecyclerView = findViewById(R.id.searchRecyclerView);




















    }
}