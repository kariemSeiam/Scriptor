package com.scriptor.scriptor2020.sections.quotes;

public class QuotesModel {

    String post;
    String quote;
    String quote_date;
    String quote_image_url;
    String username;
    String uid;
    String profile;
    Boolean isAdmin;


    public QuotesModel(String post, String username) {
        this.post = post;
        this.username = username;
    }



    public QuotesModel() {


    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuote_date() {
        return quote_date;
    }

    public void setQuote_date(String quote_date) {
        this.quote_date = quote_date;
    }

    public String getQuote_image_url() {
        return quote_image_url;
    }

    public void setQuote_image_url(String quote_image_url) {
        this.quote_image_url = quote_image_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}

