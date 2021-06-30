package com.example.quotesappuserside.Model;

public class YourQuotesModel {
    String ID, name, email, contact, quotes;

    public YourQuotesModel() {
    }

    public YourQuotesModel(String ID, String name, String email, String contact, String quotes) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.quotes = quotes;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }
}
