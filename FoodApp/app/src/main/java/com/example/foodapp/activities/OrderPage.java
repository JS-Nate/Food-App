package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.ToolbarHandler;
import com.example.foodapp.models.ModelOrder;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.foodapp.models.ModelOrderItem;
import com.example.foodapp.models.ModelMenuItem;
import java.util.List;
import com.example.foodapp.adapters.OrderItemAdapter;



public class OrderPage extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int id;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 0);
        Log.d("Received in search page", "id ->" + id);

        // Get pending order
        db = new AppDatabase(this);
        ModelOrder order = db.getCurrentOrder(id);


        if (order != null) {
            RecyclerView orderTable = findViewById(R.id.orderTable);
            orderTable.setLayoutManager(new LinearLayoutManager(this)); // or use GridLayoutManager

            List<ModelOrderItem> orderItems = db.getOrderItems(order.getId());

            for (ModelOrderItem item : orderItems) {
                ModelMenuItem menuItem = db.getMenuItem(item.getItemId()); // Your method to get menu item
                item.setItemName(menuItem.getItemName()); // Set the name in ModelOrderItem
            }

//            for (ModelOrderItem item : orderItems) {
//                Log.d("Order item", item.toString());
//            }

            OrderItemAdapter adapter = new OrderItemAdapter(orderItems);
            orderTable.setAdapter(adapter);
        }


        // buttons on screen
        homeButton = findViewById(R.id.button1);
        searchButton = findViewById(R.id.button2);
        orderButton = findViewById(R.id.button3);
        accountButton = findViewById(R.id.button4);
        // Use the ToolbarHandler to handle the image buttons
        ToolbarHandler.handleImageButtonsFromOrder(id, this, homeButton, searchButton, orderButton, accountButton);


        Button submitOrder = findViewById(R.id.submitOrder);
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add to history of order table
                // clear current order stuff form database
            }
        });
    }
}