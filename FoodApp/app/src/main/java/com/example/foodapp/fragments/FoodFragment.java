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

        AppDatabase db = new AppDatabase(view.getContext());
        ModelVendor thisVendor = db.getVendorFromId(vendorId);

        // You can find the TextView and set its text here
        TextView textView = view.findViewById(R.id.title);
//        textView.setText(thisVendor.getName() + " food list here: ");

        RecyclerView foodList = view.findViewById(R.id.foodList);
        List<ModelMenuItem> modelMenuItemList = db.getMenuItemsByVendorAndCategory(vendorId, "Food");
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        foodList.setLayoutManager(layoutManager);
        VendorFoodAdapter vendorFoodAdapter = new VendorFoodAdapter(view.getContext(), modelMenuItemList, userID);
        foodList.setAdapter(vendorFoodAdapter);


        textView.setText(" food list : " + modelMenuItemList.size());




        return view;
    }


}