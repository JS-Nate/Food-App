package com.example.foodapp.models;

public class ModelVendorImage {

    int id;
    int vendorID;
    String image;



    public ModelVendorImage() {}
    public ModelVendorImage(int vendorID, String image) {
        this.vendorID = vendorID;
        this.image = image;
    }

    public ModelVendorImage(int id, int vendorID, String image) {
        this.id = id;
        this.vendorID = vendorID;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
