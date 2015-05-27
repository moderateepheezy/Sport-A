package com.news.app.com.sport.app.model;

public class ItemNews {

    public int id;
	public String CId;
 	public String CategoryName;
	public String CategoryImage;
	public String CatId;
	public  String NewsHeading;
 	public String NewsDescription;
	public String NewsImage;
	public String NewsDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCId() {
		return CId;
	}

	public void setCId(String cid) {
		this.CId = cid;
	}
 	
	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryname) {
		this.CategoryName = categoryname;
	}
	
	public String getCategoryImage() {
		return CategoryImage;
	}

	public void setCategoryImage(String categoryimage) {
		this.CategoryImage = categoryimage;
	}
	
	
	public String getCatId() {
		return CatId;
	}

	public void setCatId(String catid) {
		this.CatId = catid;
	}
	
 	public String getNewsHeading() {
		return NewsHeading;
	}

	public void setNewsHeading(String newsheading) {
		this.NewsHeading = newsheading;
	}
	
	public String getNewsDescription() {
		return NewsDescription;
	}

	public void setNewsDescription(String newsdescription) {
		this.NewsDescription = newsdescription;
	}
	public String getNewsImage() {
		return NewsImage;
	}

	public void setNewsImage(String newsimage) {
		this.NewsImage = newsimage;
	}
	public String getNewsDate() {
		return NewsDate;
	}

	public void setNewsDate(String newsdate) {
		this.NewsDate = newsdate;
	}

}
