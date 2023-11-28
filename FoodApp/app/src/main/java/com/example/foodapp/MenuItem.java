package com.example.foodapp;

import com.example.foodapp.models.ModelVendor;

public class MenuItem {
    public Integer id;
    public ModelVendor vendor;
    public String itemName;
    public Boolean featured;
    public String description;
    public String category;
    public String image;
    public double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelVendor getVendor() {
        return vendor;
    }

    public void setVendor(ModelVendor vendor) {
        this.vendor = vendor;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFeatured() {
        return featured == Boolean.TRUE;
    }
}