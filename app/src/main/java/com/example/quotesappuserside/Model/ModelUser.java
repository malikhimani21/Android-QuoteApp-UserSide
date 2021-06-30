package com.example.quotesappuserside.Model;

public class ModelUser {

    String id, username, name, email, contact, password;

    public ModelUser() {
    }

    public ModelUser(String id, String username, String name, String email, String contact, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
