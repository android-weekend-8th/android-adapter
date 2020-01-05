package com.rathana.android_adapter.entity;

import androidx.annotation.DrawableRes;

public class Product {

    private double price;
    @DrawableRes
    private int thumbnail;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(@DrawableRes int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
