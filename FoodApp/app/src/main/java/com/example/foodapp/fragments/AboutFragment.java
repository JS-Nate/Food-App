package com.example.foodapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.foodapp.AppDatabase;
import com.example.foodapp.models.ModelVendor;


import androidx.fragment.app.Fragment;

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

    // view and info about the vendor
    private VideoView videoView;
    TextView textViewAbout;

    // database and vendor's id
    AppDatabase db;
    private int vendorId;

    // required empty constructor
    public AboutFragment(){}


    // constructor initializing the vendor id
    public AboutFragment(int vendorId) {
        this.vendorId = vendorId;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // gets the current vendor from the database
        db = new AppDatabase(getActivity());
        ModelVendor vendor = db.getVendorFromId(vendorId);

        // displays the description of the vendor
        textViewAbout.setText(vendor.getDescription());

        // gets the promotional video in the files based on the one in the database
        int videoResourceId = getResources().getIdentifier(vendor.getVendorVideo(), "raw", getActivity().getPackageName());

        // for debugging purposes
        Log.d("Video", "Video resource id: " + videoResourceId);
        Log.d("Video name", "Video name: " + vendor.getVendorVideo());

        // sets the promotional video display in the videoView
        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + videoResourceId;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // uses Media Controller to control video play/pause
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // starts the promotional video
        videoView.start();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // on screen about and video view
        textViewAbout = view.findViewById(R.id.textViewAbout);
        videoView = view.findViewById(R.id.video_view);

        return view;
    }
}
