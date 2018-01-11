package com.nagendra.android.loadingviewdemo.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nagendra singh on 10/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class ApiResponse {

    @SerializedName("message") private String message;
    @SerializedName("statusCode") private String  statusCode;
    @SerializedName("otherParams") private OtherObject otherParams;
    @SerializedName("status") private String status;
    @SerializedName("data") private List<Data> data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }


    public OtherObject getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(OtherObject otherParams) {
        this.otherParams = otherParams;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", otherParams='" + otherParams + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

}
