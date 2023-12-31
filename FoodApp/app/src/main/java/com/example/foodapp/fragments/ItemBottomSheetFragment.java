package com.example.foodapp.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.AppDatabase;
import com.example.foodapp.R;
import com.example.foodapp.models.ModelMenuItem;
import com.example.foodapp.models.ModelOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;

/* This is the floating action menu displaying a menu item's details to add them to cart */
public class ItemBottomSheetFragment extends BottomSheetDialogFragment {


    // on screen views and buttons
    TextView itemName, itemDescription, itemPrice, yourPrice;
    ImageView itemImage;
    RadioGroup radioGroup;
    EditText amount;

    // user, item and vendor id
    int userID;
    int itemID;
    int vendorID;

    double sPrice = 0;



    public ItemBottomSheetFragment() {
        // Required empty public constructor
    }

    // constructor initializing the user and item id
    public ItemBottomSheetFragment(int userID, int itemID) {
        this.userID = userID;
        this.itemID = itemID;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_item_bottom_sheet_fragment, container, false);
        // gets the current menu item from the database
        AppDatabase db = new AppDatabase(view.getContext());
        ModelMenuItem thisMenuItem = db.getMenuItem(itemID);

        // on screen views
        itemName = view.findViewById(R.id.itemName);
        itemDescription = view.findViewById(R.id.itemDescription);
        itemPrice = view.findViewById(R.id.itemPrice);
        itemImage = view.findViewById(R.id.itemImage);
        yourPrice = view.findViewById(R.id.yourPrice);
        // initial item price value
        double iPrice = Double.valueOf(thisMenuItem.getItemPrice());

        // button to choose the size of your item
        // default small selected
        RadioGroup radioGroup = view.findViewById(R.id.sizeChoice);
        RadioButton small = view.findViewById(R.id.small);
        small.setChecked(true);
        RadioButton medium = view.findViewById(R.id.medium);
        RadioButton large = view.findViewById(R.id.large);


        // displays the item's name, description, price and image
        itemName.setText(thisMenuItem.getItemName());
        itemDescription.setText(thisMenuItem.getItemDescription());
        itemPrice.setText("$" + thisMenuItem.getItemPrice());
        String imageUrl = thisMenuItem.getItemImage();
        Picasso.get().load(imageUrl).into(itemImage);


        // for the user to enter their amount
        TextView add = view.findViewById(R.id.add);
        TextView sub = view.findViewById(R.id.sub);
        TextView showAmount = view.findViewById(R.id.showAmount);
        int more = 1;

        // when the user adds to the quantity and updates the price
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentValStr=showAmount.getText().toString();
                int presentIntVal=Integer.parseInt(presentValStr);
                presentIntVal++;
                showAmount.setText(String.valueOf(presentIntVal));
            }
        });

        // when the user decreases to the quantity and updates the price
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentValStr=showAmount.getText().toString();
                int presentIntVal=Integer.parseInt(presentValStr);
                presentIntVal--;
                showAmount.setText(String.valueOf(presentIntVal));
            }
        });

        // button to add to order
        Button addToOrder = view.findViewById(R.id.addToOrder);
        // displays the initial price
        yourPrice.setText("Your Price: $" + (iPrice));


        // adds to the price based on the user's chosen size
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was selected and assigns the added price to the size
                if (checkedId == R.id.small) {
                    sPrice = 0.25;
                } else if (checkedId == R.id.medium) {
                    sPrice = 0.50;
                } else if (checkedId == R.id.large) {
                    sPrice = 0.75;
                }
                // updates the current price display
                yourPrice.setText("Your Price: $" + (Double.valueOf(showAmount.getText().toString())*(iPrice + sPrice)));
            }
        });


        // updates the current price displayed
        showAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amountText = showAmount.getText().toString().trim(); // Trim to remove leading/trailing whitespaces
                if (!amountText.isEmpty()) {
                    double totalPrice = Double.valueOf(amountText) * (iPrice + sPrice);
                    yourPrice.setText("Your Price: $" + totalPrice);
                } else {
                    yourPrice.setText("Your Price: $0.00"); // Or handle it according to your requirements
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });






        // when the user adds to their order
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    tax * (itemquantity  * (default item price + size price))
                double subTotal = 1.13*(Double.valueOf(showAmount.getText().toString())*(iPrice + sPrice));
                Toast.makeText(view.getContext(), "Adding to Order", Toast.LENGTH_SHORT).show();

                // check if an open order exists, otherwise create a new one
                ModelOrder order = db.getOrCreateOrder(userID, vendorID, LocalDate.now().toString(), subTotal);

                // add item to order
                db.addOrderItem(order.getId(), itemID, Integer.valueOf(showAmount.getText().toString()), subTotal);

                // closes the floating action menu
                dismiss();
            }
        });





        return view;

    }
}