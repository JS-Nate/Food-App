package com.example.foodapp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodapp.models.ModelOrderItem;
import java.util.List;
import androidx.annotation.NonNull;
import com.example.foodapp.R;


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
        // Bind data from item to holder views
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        // Define view elements from your order_item_layout

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            // Initialize view elements
        }
    }
}
