package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.foodapp.activities.HomePage;
import com.example.foodapp.activities.OrderPage;
import com.example.foodapp.activities.SearchPage;

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

            }
        });

    }





    // from the home page
    public static void handleImageButtonsFromHome(int id, Context context, ImageButton search, ImageButton order, ImageButton account) {
        // Add your logic for each image button

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

            }
        });



    }




    // from the search page
    public static void handleImageButtonsFromSearch(int id, Context context, ImageButton home, ImageButton order, ImageButton account) {
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

            }
        });



    }




    public static void handleImageButtonsFromOrder(int id, Context context, ImageButton home, ImageButton search, ImageButton account) {
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


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }




    public static void handleImageButtonsFromAccount(int id, Context context, ImageButton home, ImageButton search, ImageButton order) {
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

    }



















}
