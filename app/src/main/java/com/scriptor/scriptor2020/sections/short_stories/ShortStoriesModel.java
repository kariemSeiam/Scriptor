package com.scriptor.scriptor2020.sections.short_stories;

public class ShortStoriesModel {

    String story_body;
    String uid;
    String story_title;
    String username;
    Boolean is_admin;

    public ShortStoriesModel() {

    }

    public ShortStoriesModel(String story_body, String uid, String story_title, String username, Boolean is_admin) {
        this.story_body = story_body;
        this.uid = uid;
        this.story_title = story_title;
        this.username = username;
        this.is_admin = is_admin;
    }

    public String getStory_body() {
        return story_body;
    }

    public void setStory_body(String story_body) {
        this.story_body = story_body;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

}
