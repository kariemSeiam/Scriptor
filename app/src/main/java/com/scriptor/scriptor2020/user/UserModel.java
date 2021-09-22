package com.scriptor.scriptor2020.user;

public class UserModel {
    String username, mail, date_created, profile_url, uid;
    Boolean isAdmin, isStared, validation;

    public UserModel() {

    }

    public UserModel(String username, String mail, String date_created, String profile_url, String uid, Boolean isAdmin, Boolean isStared, Boolean validation) {
        this.username = username;
        this.mail = mail;
        this.date_created = date_created;
        this.profile_url = profile_url;
        this.uid = uid;
        this.isAdmin = isAdmin;
        this.isStared = isStared;
        this.validation = validation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getStared() {
        return isStared;
    }

    public void setStared(Boolean stared) {
        isStared = stared;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }
}
