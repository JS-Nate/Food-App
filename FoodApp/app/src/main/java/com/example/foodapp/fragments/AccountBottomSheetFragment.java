package com.example.foodapp.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.activities.LoginPage;
import com.example.foodapp.models.ModelUser;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AccountBottomSheetFragment extends BottomSheetDialogFragment {

    private TextView userName, userEmail, other;
    ImageButton logout;
    Button changeIcon, saveIcon;
    ImageView userImage;
    private int userID;
    Uri selectedImageUri;


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


        userImage.setImageURI(Uri.parse(thisUser.getUserImage())); // Set image URI
        selectedImageUri = Uri.parse(thisUser.getUserImage());


        other.setText(selectedImageUri.toString()); // display the string that the image is being stored as




        // ActivityResultLauncher to capturing images
        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            userImage.setImageURI(selectedImageUri);
                        }
                    }
                }
        );


        changeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bring up camera to save to imageview
                ContentValues values = new ContentValues();
                selectedImageUri = view.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImageUri);
                startCamera.launch(cameraIntent);
                other.setText(selectedImageUri.toString()); // display the string that the image is being stored as

                // update database with it
                ModelUser updatingUser = new ModelUser(
                        thisUser.getId(),
                        thisUser.getFirstName(),
                        thisUser.getLastName(),
                        thisUser.getEmail(),
                        thisUser.getPassword(),
                        selectedImageUri.toString()
                );


                int rowsAffected = db.updateUser(userID, updatingUser);
//                dismiss();
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // return to login page and finish()
                Intent intent = new Intent(v.getContext(), LoginPage.class);
                startActivity(intent);
                ((Activity) v.getContext()).finish();
            }
        });



        return view;
    }



    // Handle the image selection result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            userImage.setImageURI(selectedImageUri);
        }
    }

}