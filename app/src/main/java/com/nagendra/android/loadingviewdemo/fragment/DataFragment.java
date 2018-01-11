package com.nagendra.android.loadingviewdemo.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nagendra.android.loadingviewdemo.R;
import com.nagendra.android.loadingviewdemo.Utility.AppConfig;
import com.nagendra.android.loadingviewdemo.adapter.BaseAdapter;
import com.nagendra.android.loadingviewdemo.adapter.DataListAdapter;
import com.nagendra.android.loadingviewdemo.network.models.response.ApiResponse;
import com.nagendra.android.loadingviewdemo.network.models.response.Data;
import com.nagendra.android.loadingviewdemo.network.rest.RetrofitAPI;
import com.nagendra.android.loadingviewdemo.network.rest.RetrofitAPInterface;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;

public class DataFragment extends Fragment implements BaseAdapter.OnItemClickListener {
    public static final int PAGE_SIZE = 21;
    private int currentPage = 1;
    private List<Data> dataList = new ArrayList<>();

    private RetrofitAPInterface retrofitAPIService;
    private RecyclerView recyclerView;
    private DataListAdapter dataListAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    public DataFragment() {
    }

    public static DataFragment newInstance() {
        return new DataFragment();
    }

    public static DataFragment newInstance(Bundle extras) {
        DataFragment fragment = new DataFragment();
        fragment.setArguments(extras);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_data, container, false);
        init();
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        dataListAdapter = new DataListAdapter();
        dataListAdapter.setOnItemClickListener(this);
        dataListAdapter.setOnReloadClickListener(this);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataListAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        findDataFirstFetchCallback();

    }

    public void init() {
        Retrofit retrofit = new RetrofitAPI(AppConfig.PAGE_URL).createRetrofitClient();
        retrofitAPIService = retrofit.create(RetrofitAPInterface.class);
    }

    @SuppressLint("StaticFieldLeak")
    public void findDataFirstFetchCallback() {
        new AsyncTask<String, String, Response<ApiResponse>>() {

            @Override
            protected Response<ApiResponse> doInBackground(String... strings) {
                Response<ApiResponse> response = null;
                Call<ApiResponse> retrofitResponse = retrofitAPIService.apiResponse();
                try {
                    response = retrofitResponse.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response<ApiResponse> response) {
                try {
                    if(null != response) {
                        Log.d(TAG, "Response Code: " + response.code());
                        if (response.code() == 200) {
                            Log.d(TAG, "Response: " + response.body().getStatusCode());
                            Log.d(TAG, "Status: " + response.body().getStatus());
                            Log.d(TAG, "Status Message: " + response.body().getMessage());
                            Log.d(TAG, "Data: " + response.body().getData());

                            ApiResponse apiData = response.body();

                            if (apiData != null) {
                                List<Data> data = apiData.getData();
//                                String thumbnail = data.get(0).getThumbnail();
                                if (data != null) {
                                    if(data.size()>0)
//                                        dataList.addAll(data);
                                        dataListAdapter.addAll(data);

                                    if (data.size() >= PAGE_SIZE) {
                                        dataListAdapter.addFooter();
                                    } else {
                                        isLastPage = true;
                                    }
                                }
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("error","2");

                }
            }
        }.execute();

    }

    @SuppressLint("StaticFieldLeak")
    public void findDataNextFetchCallback() {
        new AsyncTask<String, String, Response<ApiResponse>>() {
            @Override
            protected Response<ApiResponse> doInBackground(String... strings) {
                Response<ApiResponse> response = null;
                Call<ApiResponse> retrofitResponse = retrofitAPIService.nextApiResponse();
                try {
                    response = retrofitResponse.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(Response<ApiResponse> response) {
                try {
                    if(null != response) {
                        Log.d(TAG, "Response Code: " + response.code());
                        if (response.code() == 200) {
                            Log.d(TAG, "Response: " + response.body().getStatusCode());
                            Log.d(TAG, "Status: " + response.body().getStatus());
                            Log.d(TAG, "Status Message: " + response.body().getMessage());
                            Log.d(TAG, "Data: " + response.body().getData());

                            dataListAdapter.removeFooter();
                            isLoading = false;

                            if (!response.isSuccessful()) {
                                int responseCode = response.code();
                                switch (responseCode){
                                    case 504: // 504 Unsatisfiable Request (only-if-cached)
                                        break;
                                    case 400:
                                        isLastPage = true;
                                        break;
                                }
                                return;
                            }

                            ApiResponse videosEnvelope = response.body();
                            if (videosEnvelope != null) {
                                List<Data> videos = videosEnvelope.getData();
                                if (videos != null) {
                                    if(videos.size()>0)
                                        dataListAdapter.addAll(videos);

                                    if(videos.size() >= PAGE_SIZE){
                                        dataListAdapter.addFooter();
                                    } else {
                                        isLastPage = true;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("error","2");

                }
            }
        }.execute();

    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };

    private void loadMoreItems() {
        Log.d("load","more");
        isLoading = true;
        currentPage += 1;
        findDataNextFetchCallback();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        currentPage = 1;
    }


    @Override
    public void onItemClick(int position, View view) {

    }

    private void removeListeners(){
        recyclerView.removeOnScrollListener(recyclerViewOnScrollListener);
    }
}
