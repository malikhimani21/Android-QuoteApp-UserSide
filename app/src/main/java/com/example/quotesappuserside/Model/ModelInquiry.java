package com.example.quotesappuserside.Model;

public class ModelInquiry {

    String id, name, email, contact, ask, feedback;

    public ModelInquiry() {
    }

    public ModelInquiry(String id, String name, String email, String contact, String ask, String feedback) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.ask = ask;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
