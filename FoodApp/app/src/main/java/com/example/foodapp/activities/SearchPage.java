package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.adapters.HomeVerAdapter;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    // on screen buttons
    ImageButton homeButton, searchButton, orderButton, accountButton;

    // vendor list and list adapter
    List<ModelVendor> modelVendorList;
    HomeVerAdapter homeVerAdapter;
    // user id
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

        // uses an edit text for the search bar
        EditText searchBar = findViewById(R.id.searchBar);
        // when the user enters text in the edit text
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            // filters the listed vendors based on the entered text
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });





        // vendor list adapter into the recycler view
        // database initialization
        AppDatabase db = new AppDatabase(SearchPage.this);
        RecyclerView searchRecyclerView = findViewById(R.id.searchRecyclerView);
        // gets a list of vendors form the database
        modelVendorList = db.getVendors();
        // displays the list on screen with the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(false);
        searchRecyclerView.setLayoutManager(layoutManager);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setNestedScrollingEnabled(false);
        // lists the vendors using the adapter class
        homeVerAdapter = new HomeVerAdapter(this, modelVendorList, id);
        searchRecyclerView.setAdapter(homeVerAdapter);


    }

    // added for searching by filtering based on the user search
    // returns a filtered list based on the name entered
    private void filter(String query) {
        List<ModelVendor> filteredList = new ArrayList<>();
        for (ModelVendor modelVendor : modelVendorList) {
            if (modelVendor.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(modelVendor);
            }
        }
        homeVerAdapter.filterList(filteredList);
    }





}