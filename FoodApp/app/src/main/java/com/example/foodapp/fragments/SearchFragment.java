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


    // vendor and user id
    private int vendorId;
    private int userID;

    // list of menu items
    List<ModelMenuItem> modelMenuItemList;
    // list adapter
    VendorFoodAdapter vendorFoodAdapter;

    // Required empty public constructor
    public SearchFragment(){}


    // constructor to initialize the vendor and user id
    public SearchFragment(int vendorId, int userID) {
        this.vendorId = vendorId;
        this.userID = userID;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // editText as search bar
        EditText searchItems = view.findViewById(R.id.searchItems);

        // filters the menu item list as the search bar is being changed
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

        // gets the current vendor from the database from its id
        AppDatabase db = new AppDatabase(view.getContext());
        ModelVendor thisVendor = db.getVendorFromId(vendorId);

        // recyclerview to list the menu items
        RecyclerView menuList = view.findViewById(R.id.foodList);
        // list of menu items belonging to the current vendor
        modelMenuItemList = db.getMenuItemsByVendor(vendorId);
        // layout manager for the recycler view and list
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        menuList.setLayoutManager(layoutManager);
        // uses the adapter to list the food items
        vendorFoodAdapter = new VendorFoodAdapter(view.getContext(), modelMenuItemList, userID);
        menuList.setAdapter(vendorFoodAdapter);

        return view;
    }



    // added for searching by filtering based on the user search
    private void filter(String query) {
        // filters a new list of menu items based on the user search title
        List<ModelMenuItem> filteredList = new ArrayList<>();
        for (ModelMenuItem modelMenuItem : modelMenuItemList) {
            if (modelMenuItem.getItemName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(modelMenuItem);
            }
        }
        vendorFoodAdapter.filterList(filteredList);
    }














}
