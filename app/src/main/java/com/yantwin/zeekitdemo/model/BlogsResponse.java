package com.yantwin.zeekitdemo.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class BlogsResponse {     //the Blog class will contain the blog info and the product list

    @Expose
    private String category;
    @Expose
    private String id;
    @Expose
    private String image_url;
    @Expose
    private String name;
    @Expose
    private int priority;
    @Expose
    private String user_name;
    @Expose
    private List<Products> products;

    public String getCategory() {
        return category;
    }

    public List<Products> getProductsList() {
        return products;
    }

    public void setProductsList(List<Products> productsList) {
        this.products = productsList;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}


