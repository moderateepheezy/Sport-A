package com.news.app.com.sport.app.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class ItemAllNews {

    private int CategoryId;
    private String CategoryName;
    private String CategoryImageUrl;
    public Bitmap image;


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryid) {
        this.CategoryId = categoryid;
    }


    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryname) {
        this.CategoryName = categoryname;
    }

    public String getCategoryImageurl()
    {
        return CategoryImageUrl;

    }

    public void setCategoryImageurl(String catimageurl)
    {
        this.CategoryImageUrl=catimageurl;
    }

    public ItemAllNews(String image, String title) {
        super();
        this.CategoryImageUrl = image;
        this.CategoryName = title;
    }

    public ItemAllNews(Bitmap image, String title) {
        super();
        this.image = image;
        this.CategoryName = title;
    }


    public ItemAllNews(){}

}
