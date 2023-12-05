package com.example.foodapp.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// model class for the order for all its properties
public class ModelOrder {
    private Integer id;
    private Integer userID;
    private Integer vendorID;
    private LocalDate date;
    private String status;
    private Double totalAmount;


    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setVendorID(Integer vendorID) {
        this.vendorID = vendorID;
    }

    public void setDate(String dateString) {
        // Assuming dateString is in ISO format (e.g., "2023-03-31")
        this.date = LocalDate.parse(dateString);
        // If the date is in another format, use DateTimeFormatter to parse it
        // Example: DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // this.date = LocalDate.parse(dateString, formatter);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getVendorID() {
        return vendorID;
    }

    public String getDate() {
        return date != null ? date.toString() : null;
    }

    public String getStatus() {
        return status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
