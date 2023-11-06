package com.example.foodapp;

public class MenuItem {
    public Integer id;
    public Vendor vendor;
    public String itemName;
    public Boolean featured;
    public String description;
    public String category;
    public String image;
    public double price;

    public boolean isFeatured() {
        return featured == Boolean.TRUE;
    }
}