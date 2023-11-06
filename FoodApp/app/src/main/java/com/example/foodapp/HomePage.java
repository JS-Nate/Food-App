package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    // on screen buttons
    ImageButton cartButton, accountButton, searchButton;

    //sets up the2 sidebar displays
    private DrawerLayout drawerLayout;
    private NavigationView accountView, searchbarView;

    // current user's id
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // database access initialization
        LoginDatabase db = new LoginDatabase(HomePage.this);

        // buttons on screen
        cartButton = findViewById(R.id.cartButton);
        accountButton = findViewById(R.id.accountButton);
        searchButton = findViewById(R.id.searchButton);

        // initializes the sidebar for viewing account info and searching
        drawerLayout = findViewById(R.id.drawerLayout);
        accountView = findViewById(R.id.accountView);
        searchbarView = findViewById(R.id.searchbarView);

        // the 3 textViews displaying account information in the account view sidebar
        Menu navMenu = accountView.getMenu();
        MenuItem displayFNAME = navMenu.findItem(R.id.displayFNAME);
        MenuItem displayLNAME = navMenu.findItem(R.id.displayLNAME);
        MenuItem displayEMAIL = navMenu.findItem(R.id.displayEMAIL);


        // gets the user's account info based on the id
        Intent intent = getIntent();
        int id = intent.getIntExtra("userId", 0);
        Log.d("Received", "id ->" + id);
        ModelUser modelUser = db.getUser(id);

        // sets the account view display the current user's account info
        displayFNAME.setTitle("First Name: " + modelUser.getFirstName());
        displayLNAME.setTitle("Last Name: " + modelUser.getLastName());
        displayEMAIL.setTitle(modelUser.getEmail());


        // opens the account view sidebar on the right to display user's account info
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the sidebar
                drawerLayout.openDrawer(accountView);
            }
        });

        // opens the search view sidebar on the left to display the searchbar
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the sidebar
                drawerLayout.openDrawer(searchbarView);
            }
        });

        // opens list of cart items
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}