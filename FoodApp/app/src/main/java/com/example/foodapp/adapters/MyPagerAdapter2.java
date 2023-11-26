package com.example.foodapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodapp.fragments.AboutFragment;
import com.example.foodapp.fragments.DrinksFragment;
import com.example.foodapp.fragments.FoodFragment;
import com.example.foodapp.fragments.LocationFragment;
import com.example.foodapp.fragments.SearchFragment;

public class MyPagerAdapter2 extends FragmentStateAdapter {

    public MyPagerAdapter2(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        // Return the total number of tabs
        return 5; // Adjust this based on the number of tabs you have
    }
}
