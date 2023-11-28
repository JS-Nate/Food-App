package com.example.foodapp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodapp.models.ModelOrderItem;
import com.example.foodapp.models.ModelMenuItem;
import java.util.List;
import androidx.annotation.NonNull;
import com.example.foodapp.R;
import android.widget.TextView;
import java.util.Locale;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<ModelOrderItem> orderItemList;

    public OrderItemAdapter(List<ModelOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        ModelOrderItem item = orderItemList.get(position);
        // Set the item name
        holder.tvItemName.setText(item.getItemName());

        // Set the quantity
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        // Set the price
        holder.tvPrice.setText(String.format(Locale.getDefault(), "$%.2f", item.getSubtotal()));
    }


    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;
        TextView tvQuantity;
        TextView tvPrice;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
