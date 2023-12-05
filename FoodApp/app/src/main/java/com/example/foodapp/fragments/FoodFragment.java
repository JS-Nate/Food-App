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

public class FoodFragment extends Fragment {

    // vendor and user id
    private int vendorId;
    private int userID;

    public FoodFragment() {
        // Required empty public constructor
    }

    // Update the constructor to accept an integer ID
    public FoodFragment(int vendorId, int userID) {
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

        // recycler view th lost the food items
        RecyclerView foodList = view.findViewById(R.id.foodList);
        // gets food list from database in the "Food" category
        List<ModelMenuItem> modelMenuItemList = db.getMenuItemsByVendorAndCategory(vendorId, "Food");
        // layout manager for the list
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        foodList.setLayoutManager(layoutManager);
        // displays the listed items using the specified adapter class
        VendorFoodAdapter vendorFoodAdapter = new VendorFoodAdapter(view.getContext(), modelMenuItemList, userID);
        foodList.setAdapter(vendorFoodAdapter);

        // For the title of the tab
        TextView textView = view.findViewById(R.id.title);
        textView.setText(" Food list");




        return view;
    }


}
