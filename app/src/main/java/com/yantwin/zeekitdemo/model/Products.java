package com.yantwin.zeekitdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;


public class Products implements Parcelable{  // will contain the product info
    @Expose
    private String category;
    @Expose
    private String id;
    @Expose
    private String demo_image;
    @Expose
    private String base_image;
    @Expose
    private String name;
    @Expose
    private int priority;
    @Expose
    private String price;

    public String getCategory() {
        return category;
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

    public String getDemo_image() {
        return demo_image;
    }

    public void setDemo_image(String demo_image) {
        this.demo_image = demo_image;
    }

    public String getBase_image() {
        return base_image;
    }

    public void setBase_image(String base_image) {
        this.base_image = base_image;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    protected Products(Parcel in) {
        category = in.readString();
        id = in.readString();
        demo_image = in.readString();
        base_image = in.readString();
        name = in.readString();
        priority = in.readInt();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(id);
        dest.writeString(demo_image);
        dest.writeString(base_image);
        dest.writeString(name);
        dest.writeInt(priority);
        dest.writeString(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Products> CREATOR = new Parcelable.Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
