package com.example.foodapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;

public class SearchFragment extends Fragment {



    private int vendorId;

    public SearchFragment(){}


    public SearchFragment(int vendorId) {
        this.vendorId = vendorId;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // You can find the TextView and set its text here
        TextView textView = view.findViewById(R.id.textViewSearch);
        textView.setText("Search me");

        return view;
    }
}
