package com.example.foodapp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.models.ModelOrderItem;
import com.example.foodapp.models.ModelMenuItem;
import java.util.List;
import androidx.annotation.NonNull;
import com.example.foodapp.R;
import com.squareup.picasso.Picasso;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    // list and delete function
    private List<ModelOrderItem> orderItemList;
    private static OnDeleteItemClickListener onDeleteItemClickListener; // Add this member variable


    // constructor for list and delete function
    public OrderItemAdapter(List<ModelOrderItem> orderItemList, OnDeleteItemClickListener onDeleteItemClickListener) {
        this.orderItemList = orderItemList;
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }





    // sets the item layout to use for listing
    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        ModelOrderItem item = orderItemList.get(position);

        // Set the item image
        AppDatabase db = new AppDatabase(holder.itemView.getContext());
        ModelMenuItem menuItem = db.getMenuItem(item.getItemId());
        String image = menuItem.getItemImage();
        Picasso.get().load(image).into(holder.tvImage);


        // Set the item name
        holder.tvItemName.setText(item.getItemName());

        // Set the quantity
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        // Set the price
        holder.tvPrice.setText(String.format(Locale.getDefault(), "$%.2f", item.getSubtotal()));
    }


    // return item list
    @Override
    public int getItemCount() {
        return orderItemList.size();
    }




    // deleting the current listed item
    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }


    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        // the order item's on screen elements displayed in the list
        ImageView tvImage;
        TextView tvItemName;
        TextView tvQuantity;
        TextView tvPrice;
        ImageButton tvDelete;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            // the order item's on screen elements displayed in the list
            tvImage = itemView.findViewById(R.id.tvImage);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDelete = itemView.findViewById(R.id.tvDelete);



            //lets the user delete a certain item from their order list
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDeleteItemClickListener != null) {
                        onDeleteItemClickListener.onDeleteItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
