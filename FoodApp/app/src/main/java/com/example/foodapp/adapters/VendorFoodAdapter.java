package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;

import java.util.List;

public class VendorFoodAdapter extends RecyclerView.Adapter<VendorFoodAdapter.ViewHolder> {


    private LayoutInflater inflater;
    Context context;
    private List<ModelMenuItem> modelMenuItemList;
    int userID;



    public VendorFoodAdapter(Context context, List<ModelMenuItem> list, int UserID){
        this.inflater = LayoutInflater.from(context);
        this.modelMenuItemList = list;
        this.userID = userID;
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
                    Toast.makeText(v.getContext(), "Clicked on " + itemName.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });


        }
    }
}
