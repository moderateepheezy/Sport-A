package com.news.app.com.sport.app.model;

/**
 * Created by simpumind on 4/8/15.
 */
public class PostItems {

    public String time;
    public String thumbNail;
    public String post;

    public PostItems(String time, String thumbNail, String post) {
        this.time = time;
        this.thumbNail = thumbNail;
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
