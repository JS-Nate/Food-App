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
import com.example.foodapp.activities.ForgetPassword;
import com.example.foodapp.activities.LoginPage;
import com.example.foodapp.models.ModelUser;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
/* This is the floating action menu that displays the user account information */
public class AccountBottomSheetFragment extends BottomSheetDialogFragment {

    // on screen views to display
    private TextView userName, userEmail;
    ImageButton logout;
    Button changeIcon, changeInfo;
    ImageView userImage;
    Uri selectedImageUri;

    // this user's id
    private int userID;

    public AccountBottomSheetFragment() {
        // Required empty public constructor
    }

    // constructor setting the user's ID
    public AccountBottomSheetFragment(int userID) {
        this.userID = userID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_bottom_sheet, container, false);

        // gets the current user from the database based on the current id
        AppDatabase db = new AppDatabase(view.getContext());
        ModelUser thisUser = db.getUser(userID);

        // on screen views and buttons
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        userImage = view.findViewById(R.id.userImage);
        changeIcon = view.findViewById(R.id.changeIcon);
        changeInfo = view.findViewById(R.id.changeInfo);
        logout = view.findViewById(R.id.logout);

        // displays the current user's information retrieved from the database
        userName.setText(thisUser.getFirstName() + " " + thisUser.getLastName());
        userEmail.setText(thisUser.getEmail());
        userImage.setImageURI(Uri.parse(thisUser.getUserImage())); // Set image URI
        selectedImageUri = Uri.parse(thisUser.getUserImage());





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


        // brings up the camera to change the user profile picture to a new one
        changeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bring up camera to save to imageview
                ContentValues values = new ContentValues();
                selectedImageUri = view.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImageUri);
                startCamera.launch(cameraIntent);

                // update database with the user's updated profile picture
                ModelUser updatingUser = new ModelUser(
                        thisUser.getId(),
                        thisUser.getFirstName(),
                        thisUser.getLastName(),
                        thisUser.getEmail(),
                        thisUser.getPassword(),
                        selectedImageUri.toString()
                );
                int rowsAffected = db.updateUser(userID, updatingUser);
            }
        });


        // for the user to change their account information such as their password
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForgetPassword.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });



        // logs the user out and returns to the home page
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



    // Handles the image selection result and updates the current user image holding it
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            userImage.setImageURI(selectedImageUri);
        }
    }

}