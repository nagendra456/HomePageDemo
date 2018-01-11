package com.nagendra.android.loadingviewdemo.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nagendra singh on 10/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class AppConfig {
    public static final String PAGE1_URL = "http://www.nobroker.in/api/v1/property/filter/region/ChIJLfyY2E4UrjsRVq4AjI7zgRY/?lat_lng%3D12.9279232,77.6271078%26rent%3D0,500000%26travelTime%3D30%26pageNo%3D2&source=gmail&ust=1515667866691000&usg=AFQjCNFyehBMo1wG3iO2aqVAouaUcdTN9Q";
    public static final String PAGE2_URL = "http://www.nobroker.in/api/v1/property/filter/region/ChIJLfyY2E4UrjsRVq4AjI7zgRY/?lat_lng=12.9279232,77.6271078&rent=0,500000&travelTime=30&pageNo=2";
    public static final String PAGE_URL = "http://www.nobroker.in/api/v1/property/filter/region/ChIJLfyY2E4UrjsRVq4AjI7zgRY/";



    public static void setPreferences(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getPreferences(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
