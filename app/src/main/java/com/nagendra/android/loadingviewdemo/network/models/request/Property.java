package com.nagendra.android.loadingviewdemo.network.models.request;

/**
 * Created by Nagendra singh on 17/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class Property {
    @Override
    public String toString() {
        return "Property{" +
                "propertyName='" + propertyName + '\'' +
                ", propertySize='" + propertySize + '\'' +
                ", propertyImageString='" + propertyImageString + '\'' +
                ", furnished='" + furnished + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    private String propertyName;
    private String propertySize;
    private String propertyImageString;
    private String furnished;
    private String price;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public String getPropertyImageString() {
        return propertyImageString;
    }

    public void setPropertyImageString(String propertyImageString) {
        this.propertyImageString = propertyImageString;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




}
