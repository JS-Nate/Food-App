package com.example.foodapp.models;

// model class for the menu item for all its properties

public class ModelMenuItem {
    int id;
    int vendorID;
    String itemName;
    String itemFeatured;
    String itemDescription;
    String itemCategory;
    String itemImage;
    String itemPrice;

    // Required empty public constructor
    public ModelMenuItem(){}

    // constructor for a menu item without specifying the id
    public ModelMenuItem(int vendorID, String itemName, String itemFeatured, String itemDescription, String itemCategory, String itemImage, String itemPrice){
        this.vendorID = vendorID;
        this.itemName = itemName;
        this.itemFeatured = itemFeatured;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
    }

    // constructor for a menu item with specifying the id
    public ModelMenuItem(int id, int vendorID, String itemName, String itemFeatured, String itemDescription, String itemCategory, String itemImage, String itemPrice){
        this.id = id;
        this.vendorID = vendorID;
        this.itemName = itemName;
        this.itemFeatured = itemFeatured;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemFeatured() {
        return itemFeatured;
    }

    public void setItemFeatured(String itemFeatured) {
        this.itemFeatured = itemFeatured;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
