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

public class AboutFragment extends Fragment {



    private int vendorId;

    public AboutFragment(){}


    public AboutFragment(int vendorId) {
        this.vendorId = vendorId;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // You can find the TextView and set its text here
        TextView textView = view.findViewById(R.id.textViewSearch);

        return view;
    }
}
