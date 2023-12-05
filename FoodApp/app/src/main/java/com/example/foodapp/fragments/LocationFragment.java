package com.example.foodapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.foodapp.models.ModelVendor;
import android.util.Log;


public class LocationFragment extends Fragment {

    // vendor id
    private int vendorId;

    // elements to display the map
    private MapView mapView;
    private GoogleMap gMap;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    // database
    AppDatabase db;

    // Required empty public constructor
    public LocationFragment(){}

    // constructor the initialize the vendor id
    public LocationFragment(int vendorId) {
        this.vendorId = vendorId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        db = new AppDatabase(getActivity());

        // phone number
        TextView phoneNumber = view.findViewById(R.id.phoneNumber);

        // gets the current vendor from the database based on the id
        ModelVendor modelVendor = db.getVendorFromId(vendorId);
        ImageView staticMapImageView = view.findViewById(R.id.static_map_image_view);

        // gets vendor's lat and long in the database
        String location = modelVendor.getLatitude() + "," + modelVendor.getLongitude(); // Location coordinates

        /* Uses geo location to display the address of the vendor in a static imageview */
        String size = "600x300"; // Size of the static map
        int zoom = 15; // Zoom level
        String apiKey = "AIzaSyB4ZrzZOqc1ImcxA-c9xOlMedPoz40Xl6c"; // api key

        String staticMapUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                + "center=" + location
                + "&zoom=" + zoom
                + "&size=" + size
                + "&markers=color:red%7Clabel:S%7C" + location
                + "&key=" + apiKey;

        Log.d("Static Map", "Static map url: " + staticMapUrl);
        Glide.with(this)
                .load(staticMapUrl)
                .into(staticMapImageView);


        // displays the vendor phone number contact
        String contact = "";
        contact = modelVendor.getContact();
        Log.d("Contact", "Contact: " + contact); // for debugging purposes
        phoneNumber.setText(contact);

        return view;
    }

}
