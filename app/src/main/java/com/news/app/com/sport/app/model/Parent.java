package com.news.app.com.sport.app.model;

import java.util.ArrayList;

public class Parent {
	public String parentText;
	
	private ArrayList<Child> children;

//	public Parent(int icon, String text, ArrayList<Child> children) {
//		this.parentIcon = icon;
//		this.parentText = text;
//		this.children = children;
//	}


	public String getText() {
		return parentText;
	}

	public void setText(String text) {
		this.parentText = text;
	}

	public ArrayList<Child> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Child> children) {
		this.children = children;
	}

	
	
	
}

