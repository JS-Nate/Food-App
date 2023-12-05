package com.example.foodapp.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.adapters.VendorFoodAdapter;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;

import java.util.List;
/* This is the fragment that works the EXACT same way as the food fragment to display drinks */
public class DrinksFragment extends Fragment{


    // vendor and user id
    private int vendorId;
    private int userID;

    // required empty constructor
    public DrinksFragment(){}


    // constructor initializing the the vendor and user id
    public DrinksFragment(int vendorId, int userID) {
        this.vendorId = vendorId;
        this.userID = userID;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        // gets the current vendor from the database
        AppDatabase db = new AppDatabase(view.getContext());
        ModelVendor thisVendor = db.getVendorFromId(vendorId);


        // recycler view to list drinks
        RecyclerView drinkList = view.findViewById(R.id.foodList);
        // gets menu items from the database with the "drinks" category
        List<ModelMenuItem> modelMenuItemList = db.getMenuItemsByVendorAndCategory(vendorId, "Drink");
        // layout manager for the recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        drinkList.setLayoutManager(layoutManager);
        // uses the same adapter for the food to list the drinks, since they're displayed similarly
        VendorFoodAdapter vendorFoodAdapter = new VendorFoodAdapter(view.getContext(), modelMenuItemList, userID);
        drinkList.setAdapter(vendorFoodAdapter);


        // For testing purposes, to ensure the menu list is functioning
        TextView textView = view.findViewById(R.id.title);
        textView.setText(" Drink list : ");

        return view;
    }


}
