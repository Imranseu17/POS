package com.example.root.pos.model;

public class SalesHistory {

    public  String email;

    public String productName;

    public String paymentID;

    public String amount;

    public String productQuantity;

    public String phoneNumber;

    public String paymentCharge;

    public String totalAmount;

    public String paymentMethod;

    public String salesDate;

    public SalesHistory(String email, String productName, String paymentID, String amount, String productQuantity, String phoneNumber, String paymentCharge, String totalAmount, String paymentMethod, String salesDate) {
        this.email = email;
        this.productName = productName;
        this.paymentID = paymentID;
        this.amount = amount;
        this.productQuantity = productQuantity;
        this.phoneNumber = phoneNumber;
        this.paymentCharge = paymentCharge;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.salesDate = salesDate;
    }
}
