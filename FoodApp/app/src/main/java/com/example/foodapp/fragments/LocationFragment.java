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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class LocationFragment extends Fragment {

    private int vendorId;
    private MapView mapView;
    private GoogleMap gMap;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    public LocationFragment(){}


    public LocationFragment(int vendorId) {
        this.vendorId = vendorId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        // You can find the TextView and set its text here
        TextView textView = view.findViewById(R.id.textViewSearch);

        ImageView staticMapImageView = view.findViewById(R.id.static_map_image_view);
        String location = "40.714728,-73.998672";
        String size = "600x300"; // Size of the static map
        int zoom = 15; // Zoom level
        String apiKey = "AIzaSyB4ZrzZOqc1ImcxA-c9xOlMedPoz40Xl6c"; // api key

        String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                + "center=" + location
                + "&zoom=" + zoom
                + "&size=" + size
                + "&key=" + apiKey;

        Glide.with(this)
                .load(staticMapUrl)
                .into(staticMapImageView);
        return view;
    }

}
