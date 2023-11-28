package com.example.foodapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {



    private int vendorId;
    private int userID;
    List<ModelMenuItem> modelMenuItemList;
    VendorFoodAdapter vendorFoodAdapter;

    public SearchFragment(){}


    public SearchFragment(int vendorId, int userID) {

        this.vendorId = vendorId;
        this.userID = userID;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // You can find the TextView and set its text here
        TextView textView = view.findViewById(R.id.textViewSearch);
        EditText searchItems = view.findViewById(R.id.searchItems);

        searchItems.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        AppDatabase db = new AppDatabase(view.getContext());
        ModelVendor thisVendor = db.getVendorFromId(vendorId);

        RecyclerView foodList = view.findViewById(R.id.foodList);
        modelMenuItemList = db.getMenuItemsByVendor(vendorId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        foodList.setLayoutManager(layoutManager);
        vendorFoodAdapter = new VendorFoodAdapter(view.getContext(), modelMenuItemList, userID);
        foodList.setAdapter(vendorFoodAdapter);



        return view;
    }



    // added for searching by filtering based on the user search
    private void filter(String query) {
        List<ModelMenuItem> filteredList = new ArrayList<>();
        for (ModelMenuItem modelMenuItem : modelMenuItemList) {
            if (modelMenuItem.getItemName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(modelMenuItem);
            }
        }
        vendorFoodAdapter.filterList(filteredList);
    }














}
