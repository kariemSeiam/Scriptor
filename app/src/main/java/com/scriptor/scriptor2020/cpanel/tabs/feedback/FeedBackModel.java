package com.scriptor.scriptor2020.cpanel.tabs.feedback;

public class FeedBackModel {
    String username, feedback, date_created, uid, profile_uri;

    public FeedBackModel(String username, String feedback, String date_created, String uid, String profile_uri) {
        this.username = username;
        this.feedback = feedback;
        this.date_created = date_created;
        this.uid = uid;
        this.profile_uri = profile_uri;
    }

    public FeedBackModel() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfile_uri() {
        return profile_uri;
    }

    public void setProfile_uri(String profile_uri) {
        this.profile_uri = profile_uri;
    }

}
