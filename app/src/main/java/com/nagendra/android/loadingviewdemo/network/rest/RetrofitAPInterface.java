package com.nagendra.android.loadingviewdemo.network.rest;

import com.nagendra.android.loadingviewdemo.network.models.response.ApiResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by bpkri on 12-04-2017.
 */

public interface RetrofitAPInterface {

    @GET("?lat_lng%3D12.9279232,77.6271078%26rent%3D0,500000%26travelTime%3D30%26pageNo%3D2&source=gmail&ust=1515667866691000&usg=AFQjCNFyehBMo1wG3iO2aqVAouaUcdTN9Q")
    Call<ApiResponse> apiResponse();

//    @GET("?lat_lng=12.9279232,77.6271078&rent=0,500000&travelTime=30&pageNo=2")
    @GET
    Call<ApiResponse> nextApiResponse(@Url String nextUrl);

}
