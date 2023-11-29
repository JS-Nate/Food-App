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

    private VideoView videoView;
    TextView textViewAbout;
    AppDatabase db;

    private int vendorId;

    public AboutFragment(){}


    public AboutFragment(int vendorId) {
        this.vendorId = vendorId;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new AppDatabase(getActivity());
        ModelVendor vendor = db.getVendorFromId(vendorId);
        textViewAbout.setText(vendor.getDescription());
        int videoResourceId = getResources().getIdentifier(vendor.getVendorVideo(), "raw", getActivity().getPackageName());

        Log.d("Video", "Video resource id: " + videoResourceId);
        Log.d("Video name", "Video name: " + vendor.getVendorVideo());

        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + videoResourceId;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // You can find the TextView and set its text here
        textViewAbout = view.findViewById(R.id.textViewAbout);


        videoView = view.findViewById(R.id.video_view);

        return view;
    }
}
