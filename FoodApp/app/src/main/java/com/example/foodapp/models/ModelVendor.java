package com.example.foodapp.models;
// model class for the vendor for all its properties
public class ModelVendor {
    public Integer id;

    public String name;
    public String description;
    public double longitude;
    public double latitude;
    public String contact;
    public String vendorVideo;



    // Required empty public constructor
    public ModelVendor(){}

    // class initialization of creating and storing vendor information, with and without an id
    public ModelVendor(String name, String description, double longitude, double latitude, String contact, String vendorVideo){
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact = contact;
        this.vendorVideo = vendorVideo;
    }

    public ModelVendor(int id, String name, String description, double longitude, double latitude, String contact, String vendorVideo){
        this.id = id;
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact = contact;
        this.vendorVideo = vendorVideo;
    }

    // getters and setters for each part of the stored vendor's info
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setVendorVideo(String vendorVideo) {
        this.vendorVideo = vendorVideo;
    }

    public String getVendorVideo() {
        return vendorVideo;
    }

}
