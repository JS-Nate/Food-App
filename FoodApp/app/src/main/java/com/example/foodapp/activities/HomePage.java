package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.adapters.HomeHorAdapter;
import com.example.foodapp.adapters.HomeVerAdapter;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelUser;
import com.example.foodapp.models.ModelVendor;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomePage extends AppCompatActivity {

    TextView welcomeMessage;

    // on screen buttons
    ImageButton homeButton, searchButton, orderButton, accountButton;
    RecyclerView recyclerView1, recyclerView2;
    HomeVerAdapter homeVerAdapter;
    HomeHorAdapter homeHorAdapter;
    List<ModelVendor> modelVendorList;
    List<ModelMenuItem> modelMenuItemList;


    //sets up the2 sidebar displays
    private DrawerLayout drawerLayout;
    private NavigationView accountView, searchbarView;

    private AppDatabase db;

    // current user's id
    int userID;

    private int currentTapTarget = 1; // Keeps track of the current TapTarget

    private void showNextTapTarget() {
        switch (currentTapTarget) {
            case 1:
                showHomeButtonTapTarget();
                break;
            case 2:
                showFeaturedItemsTapTarget();
                break;
            case 3:
                showSearchButtonTapTarget();
                break;
            case 4:
                showOrderButtonTapTarget();
                break;
            case 5:
                showAccountButtonTapTarget();

                break;

            // Add cases for other buttons as needed
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // database access initialization
        db = new AppDatabase(HomePage.this);


        // gets the user's account info based on the id
        Intent intent = getIntent();
        userID = intent.getIntExtra("userId", 0);
        Log.d("Received", "id ->" + userID);
        ModelUser thisUser = db.getUser(userID);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtonsFromHome(userID, this, homeButton, searchButton, orderButton, accountButton);

        welcomeMessage = findViewById(R.id.welcomeMessage);
        welcomeMessage.setText("Hi " + thisUser.getFirstName());

        showNextTapTarget();



        // initializes the sidebar for viewing account info and searching
//        drawerLayout = findViewById(R.id.drawerLayout);
//        accountView = findViewById(R.id.accountView);
//        searchbarView = findViewById(R.id.searchbarView);

        // the 3 textViews displaying account information in the account view sidebar
//        Menu navMenu = accountView.getMenu();
//        MenuItem displayFNAME = navMenu.findItem(R.id.displayFNAME);
//        MenuItem displayLNAME = navMenu.findItem(R.id.displayLNAME);
//        MenuItem displayEMAIL = navMenu.findItem(R.id.displayEMAIL);



//        // sets the account view display the current user's account info
//        displayFNAME.setTitle("First Name: " + modelUser.getFirstName());
//        displayLNAME.setTitle("Last Name: " + modelUser.getLastName());
//        displayEMAIL.setTitle(modelUser.getEmail());



//        welcomeMessage.setText("Welcome Back, " + modelUser.getFirstName());








        // the first/horizontal one
        recyclerView1 = findViewById(R.id.home_hor_rec);
        modelMenuItemList = db.getFeaturedItems();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        layoutManager1.setReverseLayout(true);
        layoutManager1.setStackFromEnd(true);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        homeHorAdapter = new HomeHorAdapter(this, modelMenuItemList, userID);
        recyclerView1.setAdapter(homeHorAdapter);


        // the second/vertical one
        recyclerView2 = findViewById(R.id.home_ver_rec);
        modelVendorList = db.getVendors();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager2.setReverseLayout(true);
        layoutManager2.setStackFromEnd(true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setNestedScrollingEnabled(false);
        homeVerAdapter = new HomeVerAdapter(this, modelVendorList, userID);
        recyclerView2.setAdapter(homeVerAdapter);






        // opens the account view sidebar on the right to display user's account info
//        accountButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open the sidebar
//                drawerLayout.openDrawer(accountView);
//            }
//        });

//        // opens the search view sidebar on the left to display the searchbar
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open the sidebar
//                drawerLayout.openDrawer(searchbarView);
//            }
//        });




    }

    private void showHomeButtonTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(homeButton, "This is the Home Button", "Experience seamless navigation to the app's main page with just a click!")
                        .outerCircleColor(R.color.app_dark_gray)
                        .outerCircleAlpha(0.9f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        // Handle click event if needed
                        currentTapTarget++;
                        showNextTapTarget();
                    }
                });
    }

    private void showFeaturedItemsTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(recyclerView1, "See Featured Items", "")
                        .outerCircleColor(R.color.app_red)
                        .outerCircleAlpha(0.9f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(100),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        // Handle click event if needed
                        currentTapTarget++;
                        showNextTapTarget();
                    }
                });
    }

    private void showSearchButtonTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(searchButton, "Search for Restaurants", "Discover diverse restaurants by simply tapping this button.")
                        .outerCircleColor(R.color.app_beige)
                        .outerCircleAlpha(0.9f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        // Handle click event if needed
                        currentTapTarget++;
                        showNextTapTarget();
                    }
                });
    }

    private void showOrderButtonTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(orderButton, "View Orders", "Tap the bag icon to explore the current contents of your shopping bag!")
                        .outerCircleColor(R.color.app_brown)
                        .outerCircleAlpha(0.9f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        // Handle click event if needed
                        currentTapTarget++;
                        showNextTapTarget();
                    }
                });
    }

    private void showAccountButtonTapTarget() {
        TapTargetView.showFor(this,
                TapTarget.forView(accountButton, "User Profile", "Your profile information is right at your fingertips—explore it here!")
                        .outerCircleColor(R.color.app_mid_gray)
                        .outerCircleAlpha(0.9f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(60),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
//                        // Handle click event if needed
//                        currentTapTarget++;
//                        showNextTapTarget();
                    }
                });
    }

}