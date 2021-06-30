package com.example.quotesappuserside.Model;

public class ContactModel {

    String id;
    String num1;
    String num2;
    String email;
    String website;
    String address;

    public ContactModel() {
    }

    public ContactModel(String id, String num1, String num2, String email, String website, String address) {
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
        this.email = email;
        this.website = website;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
