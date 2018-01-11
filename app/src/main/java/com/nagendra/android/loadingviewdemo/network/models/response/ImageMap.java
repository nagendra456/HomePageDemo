package com.nagendra.android.loadingviewdemo.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nagendra singh on 10/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class ImageMap
{

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @SerializedName("thumbnail") private String  thumbnail;


    private String original;

    private String medium;

    private String large;



    public String getOriginal ()
    {
        return original;
    }

    public void setOriginal (String original)
    {
        this.original = original;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    public String getLarge ()
    {
        return large;
    }

    public void setLarge (String large)
    {
        this.large = large;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [thumbnail = "+thumbnail+", original = "+original+", medium = "+medium+", large = "+large+"]";
    }
}

