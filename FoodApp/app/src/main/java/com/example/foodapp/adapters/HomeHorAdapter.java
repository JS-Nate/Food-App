package com.example.foodapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.activities.ItemDetails;
import com.example.foodapp.activities.VendorDetails;
import com.example.foodapp.fragments.AccountBottomSheetFragment;
import com.example.foodapp.fragments.ItemBottomSheetFragment;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;
import com.squareup.picasso.Picasso;


// Adapter class for the horizontal list of items in the home page

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {


    private LayoutInflater inflater;
    Context context;
    private List<ModelMenuItem> modelMenuItemList;
    int userID;
    int itemID;

    public HomeHorAdapter(Context context, List<ModelMenuItem> list, int userID){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.modelMenuItemList = list;
        this.userID = userID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorAdapter.ViewHolder holder, int position) {

        AppDatabase db = new AppDatabase(context);

        ModelMenuItem modelMenuItem = modelMenuItemList.get(position);
        holder.itemName.setText(modelMenuItem.getItemName());

        String itemVendorName = db.getVendorName(modelMenuItem.getVendorID());
        holder.itemVendor.setText(itemVendorName);

        String itemImage = modelMenuItem.getItemImage();
        Picasso.get().load(itemImage).into(holder.itemImage);


    }

    @Override
    public int getItemCount() {
        return modelMenuItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemVendor;
        ImageView itemImage;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemVendor = itemView.findViewById(R.id.itemVendor);
            itemImage = itemView.findViewById(R.id.itemImage);
            cardView = itemView.findViewById(R.id.cardView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked " + modelMenuItemList.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
//                    // Create an intent to start the ItemDetailActivity
//                    Intent intent = new Intent(context, ItemDetails.class);
//
//                    // Pass any necessary data to the detail activity using intent extras
//                    intent.putExtra("item_id", modelMenuItemList.get(getAdapterPosition()).getId());
//                    intent.putExtra("userID", userID);
//                    // Add other data as needed
//
//                    // Start the detail activity
//                    context.startActivity(intent);
//
//
//
                    showItemBottomSheet(v.getContext(), userID, modelMenuItemList.get(getAdapterPosition()).getId());

                }
            });




        }

    }






    private static void showItemBottomSheet(Context context, int userID, int itemID) {
        ItemBottomSheetFragment itemBottomSheetFragment = new ItemBottomSheetFragment(userID, itemID);
        itemBottomSheetFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), itemBottomSheetFragment.getTag());
    }





}
