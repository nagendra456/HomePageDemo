package com.nagendra.android.loadingviewdemo.network.rest;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nagendra singh on 25/07/2017.
 */
public class RetrofitAPI {

    private String URL;

    public RetrofitAPI(String URL) {
        this.URL = URL;
    }

    public Retrofit createRetrofitClient() {
        OkHttpClient client_timeout = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS).writeTimeout(30000,TimeUnit.SECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS).build();


        Retrofit client = new Retrofit.Builder()
                .baseUrl(this.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client_timeout)
                .build();
        return client;
    }
}
