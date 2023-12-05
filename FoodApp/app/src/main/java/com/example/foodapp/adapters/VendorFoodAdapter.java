package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.fragments.ItemBottomSheetFragment;
import com.example.foodapp.models.ModelMenuItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/* Adapter for listing food and drink items in the vendor fod/drink tab */
public class VendorFoodAdapter extends RecyclerView.Adapter<VendorFoodAdapter.ViewHolder> {

    // list layout and context
    private LayoutInflater inflater;
    Context context;
    private List<ModelMenuItem> modelMenuItemList;
    // user ID
    int userID;



    public VendorFoodAdapter(Context context, List<ModelMenuItem> list, int userID){
        this.inflater = LayoutInflater.from(context);
        this.modelMenuItemList = list;
        this.userID = userID;
    }

    public void filterList(List<ModelMenuItem> filteredList) {
        modelMenuItemList = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public VendorFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vendor_food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorFoodAdapter.ViewHolder holder, int position) {
        // the current menu item object
        ModelMenuItem modelMenuItem = modelMenuItemList.get(position);
        // display the item name, image and price in the list
        holder.itemName.setText(modelMenuItem.getItemName());
        holder.itemPrice.setText("$" + String.valueOf(modelMenuItem.getItemPrice())); // Assuming ItemPrice is int, update accordingly
        String imageString = modelMenuItem.getItemImage();

        // trying to load web-link based image via Picasso implementation
        Picasso.get().load(imageString).into((holder.itemImage));
    }

    @Override
    public int getItemCount() {return modelMenuItemList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        // item name, price and image view
        TextView itemName, itemPrice;
        ImageView itemImage;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // item name, price and image view
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                // clicking on an item will bring up that item's detail menu to change the size and amount to add to order
                public void onClick(View v) {
                    showItemBottomSheet(v.getContext(), userID, modelMenuItemList.get(getAdapterPosition()).getId());
                }
            });
        }
    }




    // for bringing up that item's detail menu
    private static void showItemBottomSheet(Context context, int userID, int itemID) {
        ItemBottomSheetFragment itemBottomSheetFragment = new ItemBottomSheetFragment(userID, itemID);
        itemBottomSheetFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), itemBottomSheetFragment.getTag());
    }



}
