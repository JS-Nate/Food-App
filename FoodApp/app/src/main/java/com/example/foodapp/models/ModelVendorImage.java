package com.example.foodapp.models;

// model class for the images of different for all its properties
public class ModelVendorImage {

    int id;
    int vendorID;
    String image;


    // class initialization of creating and storing image information, with and without an id
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

    // getters and setters for each part of the stored image's info
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
