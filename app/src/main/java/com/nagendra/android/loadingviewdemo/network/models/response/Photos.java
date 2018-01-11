package com.nagendra.android.loadingviewdemo.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nagendra singh on 10/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class Photos {

    private String title;


    public ImageMap getImagesMap() {
        return imagesMap;
    }

    public void setImagesMap(ImageMap imagesMap) {
        this.imagesMap = imagesMap;
    }

    @SerializedName("imagesMap") private ImageMap imagesMap;


    private String name;

    private Boolean displayPic;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }


    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Boolean getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(Boolean displayPic) {
        this.displayPic = displayPic;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", imagesMap = "+imagesMap+", name = "+name+", displayPic = "+displayPic+"]";
    }

}
