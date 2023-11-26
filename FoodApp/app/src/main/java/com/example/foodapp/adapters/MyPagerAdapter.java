package com.example.foodapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.foodapp.fragments.FoodFragment;
import com.example.foodapp.fragments.DrinksFragment;
import com.example.foodapp.fragments.SearchFragment;
import com.example.foodapp.fragments.AboutFragment;
import com.example.foodapp.fragments.LocationFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // Return the fragment for the given position
        switch (position) {
            case 0:
                return new FoodFragment(); // Replace with your FoodFragment
            case 1:
                return new DrinksFragment(); // Replace with your DrinksFragment
            case 2:
                return new SearchFragment(); // Replace with your SearchFragment
            case 3:
                return new AboutFragment(); // Replace with your AboutFragment
            case 4:
                return new LocationFragment(); // Replace with your LocationFragment
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Return the total number of tabs
        return 5; // Adjust this based on the number of tabs you have
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Return the title for each tab
        switch (position) {
            case 0:
                return "Food";
            case 1:
                return "Drinks";
            case 2:
                return "Search";
            case 3:
                return "About";
            case 4:
                return "Location";
            default:
                return null;
        }
    }
}
