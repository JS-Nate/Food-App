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

import java.util.List;

import androidx.cardview.widget.CardView;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.fragments.ItemBottomSheetFragment;
import com.example.foodapp.models.ModelMenuItem;
import com.squareup.picasso.Picasso;


// Adapter class for the horizontal list of items in the home page

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {


    // layout and list variables for listing
    private LayoutInflater inflater;
    Context context;
    private List<ModelMenuItem> modelMenuItemList;

    // relevant user and item id's
    int userID;
    int itemID;

    // constructor for the local list variables
    public HomeHorAdapter(Context context, List<ModelMenuItem> list, int userID){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.modelMenuItemList = list;
        this.userID = userID;
    }

    // sets the item layout it uses for listing
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorAdapter.ViewHolder holder, int position) {

        // database initialization
        AppDatabase db = new AppDatabase(context);

        // display views from the values in the database
        ModelMenuItem modelMenuItem = modelMenuItemList.get(position);
        holder.itemName.setText(modelMenuItem.getItemName());

        String itemVendorName = db.getVendorName(modelMenuItem.getVendorID());
        holder.itemVendor.setText(itemVendorName);

        String itemImage = modelMenuItem.getItemImage();
        Picasso.get().load(itemImage).into(holder.itemImage);


    }

    // list size
    @Override
    public int getItemCount() {
        return modelMenuItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // display views
        TextView itemName, itemVendor;
        ImageView itemImage;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // display views
            itemName = itemView.findViewById(R.id.itemName);
            itemVendor = itemView.findViewById(R.id.itemVendor);
            itemImage = itemView.findViewById(R.id.itemImage);
            cardView = itemView.findViewById(R.id.cardView);


            // when the user clicks on a menu item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // brings up the bottom menu to add the item to your order
                    showItemBottomSheet(v.getContext(), userID, modelMenuItemList.get(getAdapterPosition()).getId());

                }
            });

        }

    }





    // for bringing up the bottom item menu
    private static void showItemBottomSheet(Context context, int userID, int itemID) {
        ItemBottomSheetFragment itemBottomSheetFragment = new ItemBottomSheetFragment(userID, itemID);
        itemBottomSheetFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), itemBottomSheetFragment.getTag());
    }





}
