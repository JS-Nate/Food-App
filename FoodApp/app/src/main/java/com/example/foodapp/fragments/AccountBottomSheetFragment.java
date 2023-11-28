package com.example.foodapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelUser;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AccountBottomSheetFragment extends BottomSheetDialogFragment {

    private TextView userName, userEmail, other;
    ImageButton changeIcon, logout;
    ImageView userImage;
    private int userID;

    public AccountBottomSheetFragment() {
        // Required empty public constructor
    }

    public AccountBottomSheetFragment(int userID) {
        this.userID = userID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_bottom_sheet, container, false);
        AppDatabase db = new AppDatabase(view.getContext());

        ModelUser thisUser = db.getUser(userID);

        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        userImage = view.findViewById(R.id.userImage);
        changeIcon = view.findViewById(R.id.changeIcon);
        logout = view.findViewById(R.id.logout);
        other = view.findViewById(R.id.other);


        // Retrieve information or set it based on your requirements
        userName.setText(thisUser.getFirstName() + " " + thisUser.getLastName());
        userEmail.setText(thisUser.getEmail());
        other.setText(thisUser.getUserImage());


        changeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bring up camera to save to imageview
                // update database with it
                // close the bottomsheet popup
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to login page and finish()
            }
        });



        return view;
    }
}
