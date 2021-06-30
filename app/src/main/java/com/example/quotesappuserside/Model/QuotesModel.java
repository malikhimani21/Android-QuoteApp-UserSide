package com.example.quotesappuserside.Model;

public class QuotesModel {
    String id, qt_cat_id, qt_cat, quote;

    public QuotesModel() {
    }

    public QuotesModel(String id, String qt_cat_id, String qt_cat, String quote) {
        this.id = id;
        this.qt_cat_id = qt_cat_id;
        this.qt_cat = qt_cat;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQt_cat_id() {
        return qt_cat_id;
    }

    public void setQt_cat_id(String qt_cat_id) {
        this.qt_cat_id = qt_cat_id;
    }

    public String getQt_cat() {
        return qt_cat;
    }

    public void setQt_cat(String qt_cat) {
        this.qt_cat = qt_cat;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
