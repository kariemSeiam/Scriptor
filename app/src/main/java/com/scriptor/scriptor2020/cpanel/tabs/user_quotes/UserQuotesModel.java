package com.scriptor.scriptor2020.cpanel.tabs.user_quotes;

public class UserQuotesModel {
    String profile, quote, username, uid, quote_image_url, quote_date;
    Boolean isAdmin;

    public UserQuotesModel() {

    }

    public UserQuotesModel(String profile, String quote, String username, String uid, String quote_image_url, String quote_date, Boolean isAdmin) {
        this.profile = profile;
        this.quote = quote;
        this.username = username;
        this.uid = uid;
        this.quote_image_url = quote_image_url;
        this.quote_date = quote_date;
        this.isAdmin = isAdmin;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQuote_image_url() {
        return quote_image_url;
    }

    public void setQuote_image_url(String quote_image_url) {
        this.quote_image_url = quote_image_url;
    }

    public String getQuote_date() {
        return quote_date;
    }

    public void setQuote_date(String quote_date) {
        this.quote_date = quote_date;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
