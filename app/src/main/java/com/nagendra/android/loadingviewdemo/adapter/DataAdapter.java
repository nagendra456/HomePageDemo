package com.nagendra.android.loadingviewdemo.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nagendra.android.loadingviewdemo.R;
import com.nagendra.android.loadingviewdemo.network.models.request.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nagendra singh on 16/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    List<Property> list = new ArrayList<Property>();
    Context context;


    public DataAdapter(List<Property> filteredData, Context context) {
        this.context=context;
        this.list.addAll(filteredData);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.property_row, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.DataViewHolder holder, int position) {
        final Property dataList = list.get(position);
        holder._description.setText(dataList.getPropertyName());
        Log.d("name::",dataList.getPropertyName());

    }

    public void swapItems(List<Property> list) {
        Log.d("swap","swap");
        final DataDiffCallbacks diffCallback = new DataDiffCallbacks(this.list, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.list.clear();
        this.list.addAll(list);
        diffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView _thumbnailImage;
        TextView _description;
        TextView  _priceTag;
        TextView _furnished;
        TextView _areaSqFt;

        public DataViewHolder(View itemView) {
            super(itemView);

            _thumbnailImage = itemView.findViewById(R.id.iv_home_thumbnail);
            _description = itemView.findViewById(R.id.tv_description);
            _priceTag = itemView.findViewById(R.id.tv_price);
            _furnished = itemView.findViewById(R.id.tv_furnished);
            _areaSqFt = itemView.findViewById(R.id.tv_area);
        }
    }

    public void clearList() {
        this.list.clear();
    }
}
