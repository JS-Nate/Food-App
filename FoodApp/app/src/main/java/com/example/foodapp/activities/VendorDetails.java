package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.adapters.MyPagerAdapter;
import com.example.foodapp.adapters.MyPagerAdapter2;
import com.example.foodapp.fragments.AboutFragment;
import com.example.foodapp.fragments.DrinksFragment;
import com.example.foodapp.fragments.FoodFragment;
import com.example.foodapp.fragments.LocationFragment;
import com.example.foodapp.fragments.SearchFragment;
import com.example.foodapp.models.ModelVendor;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class VendorDetails extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;

    int id;
    TextView name, restaurantDescription;

    TabLayout tabLayout;
    ViewPager viewPager;
//    ViewPager2 viewPager;

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

        name = findViewById(R.id.restaurantTitle);
        name.setText(modelVendor.getName().toString().trim());

        restaurantDescription = findViewById(R.id.restaurantDescription);
        restaurantDescription.setText(modelVendor.getDescription().toString().trim());

        /* old */
//        // Set up ViewPager
        viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Set up TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        /* new */
        // Set up ViewPager
//        viewPager = findViewById(R.id.viewPager);
//        MyPagerAdapter2 pagerAdapter = new MyPagerAdapter2(this);
//        viewPager.setAdapter(pagerAdapter);
//
//        // Set up TabLayout
//        tabLayout = findViewById(R.id.tabLayout);
//
//        // Attach TabLayout to ViewPager2
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> tab.setText(pagerAdapter.createFragment(position).toString())
//        ).attach();


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FoodFragment(), "Food");
        adapter.addFragment(new DrinksFragment(), "Drinks");
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new AboutFragment(), "About");
        adapter.addFragment(new LocationFragment(), "Location");
        viewPager.setAdapter(adapter);
    }



    // Adapter for the ViewPager
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }











}


