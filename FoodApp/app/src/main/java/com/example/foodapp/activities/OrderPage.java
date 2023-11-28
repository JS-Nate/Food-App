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
import android.widget.Toast;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderPage extends AppCompatActivity {
    ImageButton homeButton, searchButton, orderButton, accountButton;
    int id;
    AppDatabase db;
    Double totalAmount;
    ModelOrder order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        Intent intent = getIntent();
        id = intent.getIntExtra("userId", 0);
        Log.d("Received in search page", "id ->" + id);

        // Get pending order
        db = new AppDatabase(this);
        order = db.getCurrentOrder(id);
        totalAmount = 0.0;
        if (order != null) {
            RecyclerView orderTable = findViewById(R.id.orderTable);
            orderTable.setLayoutManager(new LinearLayoutManager(this)); // or use GridLayoutManager

            List<ModelOrderItem> orderItems = db.getOrderItems(order.getId());

            for (ModelOrderItem item : orderItems) {
                ModelMenuItem menuItem = db.getMenuItem(item.getItemId()); // Your method to get menu item
                item.setItemName(menuItem.getItemName()); // Set the name in ModelOrderItem
                totalAmount = totalAmount + item.getSubtotal();
            }

            // add the list of items
            OrderItemAdapter adapter = new OrderItemAdapter(orderItems);
            orderTable.setAdapter(adapter);

            // display total
            TextView totalText = findViewById(R.id.totalAmount);

            // Assuming totalAmount is a Number (like Integer, Double, etc.)
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
            String formattedTotal = currencyFormat.format(totalAmount);

            totalText.setText(formattedTotal);
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
                if (order == null) {
                    return;
                }
             //   db.setOrderStatus(order.getId(),"Submitted");

                Intent intent = new Intent(OrderPage.this, PaymentInformation.class);
                intent.putExtra("userId", id);
                Log.d("from toolbar Sent", "id = " + id);
                startActivity(intent);


             //   Toast.makeText(getApplicationContext(), "Your order has been submitted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}