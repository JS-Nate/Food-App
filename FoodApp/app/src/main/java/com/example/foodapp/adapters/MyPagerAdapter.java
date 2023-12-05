package com.example.foodapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.foodapp.fragments.FoodFragment;
import com.example.foodapp.fragments.DrinksFragment;
import com.example.foodapp.fragments.SearchFragment;
import com.example.foodapp.fragments.AboutFragment;
import com.example.foodapp.fragments.LocationFragment;

/* Adapter class for the 5 tab pages in the vendor details page */
public class MyPagerAdapter extends FragmentPagerAdapter {

    // the userid and the id of the vendor it's displaying
    int vendorID;
    int userID;


    // constructor getting the userid and the id of the vendor it's displaying
    public MyPagerAdapter(FragmentManager fm, int id, int userID) {

        super(fm);
        this.vendorID = id;
        this.userID = userID;
    }

    @Override
    public Fragment getItem(int position) {
        // Return the fragment for the given position
        switch (position) {
            case 0:
                return new FoodFragment(vendorID, userID); // Food fragment page
            case 1:
                return new DrinksFragment(vendorID, userID); // DrinksFragment page
            case 2:
                return new SearchFragment(vendorID, userID); // SearchFragment page
            case 3:
                return new AboutFragment(vendorID); // AboutFragment page
            case 4:
                return new LocationFragment(vendorID); // LocationFragment page
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Return the total number of tabs
        return 5;
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
