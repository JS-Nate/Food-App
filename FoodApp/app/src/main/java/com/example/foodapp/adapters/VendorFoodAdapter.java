package com.example.foodapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.activities.ItemDetails;
import com.example.foodapp.fragments.ItemBottomSheetFragment;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VendorFoodAdapter extends RecyclerView.Adapter<VendorFoodAdapter.ViewHolder> {


    private LayoutInflater inflater;
    Context context;
    private List<ModelMenuItem> modelMenuItemList;
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
        ModelMenuItem modelMenuItem = modelMenuItemList.get(position);
        holder.itemName.setText(modelMenuItem.getItemName());
        holder.itemPrice.setText(String.valueOf(modelMenuItem.getItemPrice())); // Assuming ItemPrice is int, update accordingly
        String imageString = modelMenuItem.getItemImage();

        // trying to load image via Picasso
        Picasso.get().load(imageString).into((holder.itemImage));
    }

    @Override
    public int getItemCount() {return modelMenuItemList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice;
        ImageView itemImage;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked on " + itemName.getText().toString() + " user id " + userID, Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(v.getContext(), ItemDetails.class);
//
//                    // Pass any necessary data to the detail activity using intent extras
//                    intent.putExtra("item_id", modelMenuItemList.get(getAdapterPosition()).getId());
//                    intent.putExtra("userID", userID);
//                    // Add other data as needed
//
//                    // Start the detail activity
//                    v.getContext().startActivity(intent);

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
