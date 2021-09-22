package com.scriptor.scriptor2020.sections.novels;

public class NovelModel {

    String novel_name, uid, novel_url, username;
    Boolean is_admin;

    public NovelModel(String novel_name, String uid, String novel_url, String username, Boolean is_admin) {
        this.novel_name = novel_name;
        this.uid = uid;
        this.novel_url = novel_url;
        this.username = username;
        this.is_admin = is_admin;
    }

    public NovelModel() {

    }

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNovel_url() {
        return novel_url;
    }

    public void setNovel_url(String novel_url) {
        this.novel_url = novel_url;
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
