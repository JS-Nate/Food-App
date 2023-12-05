package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.adapters.MyPagerAdapter;
import com.example.foodapp.fragments.AboutFragment;
import com.example.foodapp.fragments.DrinksFragment;
import com.example.foodapp.fragments.FoodFragment;
import com.example.foodapp.fragments.LocationFragment;
import com.example.foodapp.fragments.SearchFragment;
import com.example.foodapp.models.ModelVendor;
import com.example.foodapp.models.ModelVendorImage;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VendorDetails extends AppCompatActivity {
    // on screen views and buttons
    ImageButton homeButton, searchButton, orderButton, accountButton;
    TextView name, restaurantDescription;
    TabLayout tabLayout;
    ViewPager viewPager;

    // current vendor's id
    int vendorId;

    // to display the vendor images in a slideshow
    private ImageView vendorImage;
    private List<ModelVendorImage> vendorImages;
    private int currentPosition = 0;
    private static final int SLIDESHOW_INTERVAL = 4000;
    private final Handler handler = new Handler();

    // Defines the runnable that will be executed to update the image
    private final Runnable updateImageRunnable = new Runnable() {
        @Override
        public void run() {
            updateImage();
            handler.postDelayed(this, SLIDESHOW_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);

        // database initialization
        AppDatabase db = new AppDatabase(this);
        // gets vendor and user id from intent
        Intent intent = getIntent();
        vendorId = intent.getIntExtra("vendorID", 0);
        int userID = intent.getIntExtra("userID", 0);

        // vendor object based on the id
        ModelVendor modelVendor = db.getVendorFromId(vendorId);

        // for debugging
        Log.d("Received in vendor page", "id ->" + userID);

        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtons(userID, this, homeButton, searchButton, orderButton, accountButton);

        // displays vendor name
        name = findViewById(R.id.restaurantTitle);
        name.setText(modelVendor.getName().toString().trim());

        // list of vendor images
        vendorImage = findViewById(R.id.displayImages);
        this.vendorImages = db.getVendorImages(vendorId);



        // Set up ViewPager for switching between the 5 tabs
        viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), vendorId, userID);
        viewPager.setAdapter(pagerAdapter);

        // Set up TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


        // Start the slideshow
        startSlideshow();

    }




    private void startSlideshow() {
        // Start the slideshow when the activity is created
        handler.postDelayed(updateImageRunnable, SLIDESHOW_INTERVAL);
    }

    private void updateImage() {
        if (vendorImages != null && !vendorImages.isEmpty()) {
            // Update the image based on the current position in the list
            Picasso.get().load(vendorImages.get(currentPosition).getImage()).into(vendorImage);

            // Move to the next position in the list
            currentPosition = (currentPosition + 1) % vendorImages.size();
        }
    }

    @Override
    protected void onDestroy() {
        // Stop the slideshow when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(updateImageRunnable);
        super.onDestroy();
    }


}


