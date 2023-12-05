package com.example.foodapp.adapters;
/*
 * this is gonna display either:
 * a list of restaurants
 * a list of categories of restaurants
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.example.foodapp.activities.VendorDetails;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelVendor;
import com.example.foodapp.models.ModelVendorImage;
import com.squareup.picasso.Picasso;

import java.util.List;

// Adapter class for the vertical list of items in the home page
public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    // list elements an id
    private LayoutInflater inflater;
    Context context;
    private List<ModelVendor> modelVendorList;
    int userID;

    // constructor
    public HomeVerAdapter(Context context, List<ModelVendor> list, int userID) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.modelVendorList = list;
        this.userID = userID;
    }


    // function to display filtered list of items
    public void filterList(List<ModelVendor> filteredList) {
        modelVendorList = filteredList;
        notifyDataSetChanged();
    }

    // sets the layout item it's displaying listed info in
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_vertical_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {
        // database and current vendor initialization
        AppDatabase db = new AppDatabase(context);
        ModelVendor modelVendor = modelVendorList.get(position);

        // gets venor images
        List<ModelVendorImage> vendorImages = db.getVendorImages(modelVendor.getId());

        // Check if the list is not empty before accessing its elements
        if (!vendorImages.isEmpty()) {
            // gets the vendor's first image to use as the icon
            ModelVendorImage chosenModelVendorImage = vendorImages.get(0);
            String thisImage = chosenModelVendorImage.getImage();
            Picasso.get().load(thisImage).into(holder.vendorImage);
        } else {
            // Handles the case where there are no images
            holder.vendorImage.setImageResource(R.drawable.no_image);
        }


        // sets the name
        holder.name.setText(modelVendor.getName());
    }

    // list size
    @Override
    public int getItemCount() {
        return modelVendorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // on screen views
        TextView name, description;
        ImageView vendorImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // on screen views
            name = itemView.findViewById(R.id.vendorName);
            description = itemView.findViewById(R.id.vendorDescription);
            vendorImage = itemView.findViewById(R.id.vendorImage);
            cardView = itemView.findViewById(R.id.cardView);


            //navigates to display the selected vendor's page sending it's id
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), VendorDetails.class);
                    intent.putExtra("vendorID", modelVendorList.get(getAdapterPosition()).getId());
                    intent.putExtra("userID", userID);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
