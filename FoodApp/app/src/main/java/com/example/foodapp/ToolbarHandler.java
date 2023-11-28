package com.example.foodapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.foodapp.activities.HomePage;
import com.example.foodapp.activities.OrderPage;
import com.example.foodapp.activities.SearchPage;
import com.example.foodapp.fragments.AccountBottomSheetFragment;

public class ToolbarHandler {


    public static void handleImageButtons(int id, Context context, ImageButton home, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomePage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                context.startActivity(intent);

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "search pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), SearchPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                context.startActivity(intent);

            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountBottomSheet(v.getContext(), id);

            }
        });

    }





    // from the home page
    public static void handleImageButtonsFromHome(int id, Context context, ImageButton home, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button


        // Change color to a specific color
        int color = ContextCompat.getColor(context, R.color.blue);
        home.setImageTintList(ColorStateList.valueOf(color));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "search pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), SearchPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();


            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountBottomSheet(v.getContext(), id);
            }
        });



    }




    // from the search page
    public static void handleImageButtonsFromSearch(int id, Context context, ImageButton home, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button

        // Change color to a specific color
        int color = ContextCompat.getColor(context, R.color.blue);
        search.setImageTintList(ColorStateList.valueOf(color));


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomePage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();

            }
        });



        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();

            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountBottomSheet(v.getContext(), id);

            }
        });



    }




    public static void handleImageButtonsFromOrder(int id, Context context, ImageButton home, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button

        // Change color to a specific color
        int color = ContextCompat.getColor(context, R.color.blue);
        order.setImageTintList(ColorStateList.valueOf(color));


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomePage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "search pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), SearchPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();

            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountBottomSheet(v.getContext(), id);

            }
        });



    }




    public static void handleImageButtonsFromAccount(int id, Context context, ImageButton home, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button


        // Change color to a specific color
        int color = ContextCompat.getColor(context, R.color.blue);
        account.setImageTintList(ColorStateList.valueOf(color));


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomePage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "search pressed", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), SearchPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();

            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderPage.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                v.getContext().startActivity(intent);
                ((Activity) v.getContext()).finish();
            }
        });

    }










    private static void showAccountBottomSheet(Context context, int userID) {
        AccountBottomSheetFragment bottomSheetFragment = new AccountBottomSheetFragment(userID);
        bottomSheetFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), bottomSheetFragment.getTag());
    }








}
