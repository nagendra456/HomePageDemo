package com.nagendra.android.loadingviewdemo.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nagendra.android.loadingviewdemo.R;
import com.nagendra.android.loadingviewdemo.fragment.DataFragment;
import com.nagendra.android.loadingviewdemo.network.models.response.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nagendra singh on 10/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class DataListAdapter extends BaseAdapter<Data> {

    public DataListAdapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_row, parent, false);

        final DataViewHolder holder = new DataViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPos, holder.itemView);
                    }
                }
            }
        });

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final DataViewHolder holder = (DataViewHolder) viewHolder;
        final Data data = getItem(position);
        String namePrint = data.getPropertyTitle();
        Log.d("name_print",namePrint);
        if (data != null) {
            holder.bind(data);
        }

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    public void addFooter() {

    }


    public class DataViewHolder extends RecyclerView.ViewHolder  {
        private ImageView thumbnail;

        @BindView(R.id.iv_home_thumbnail)
        ImageView _thumbnailImage;
        @BindView(R.id.tv_description)
        TextView _description;
        @BindView(R.id.tv_price)
        TextView  _priceTag;
        @BindView(R.id.tv_furnished)
        TextView _furnished;
        @BindView(R.id.tv_area)
        TextView _areaSqFt;

        public DataViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.iv_home_thumbnail);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Data data){
            setUpTitle(_description, data);
            setUpSubtitle(_priceTag, data);
            setUpFurnishedText(_furnished, data);
            setUpArea(_areaSqFt, data);
            setUpThumbnail(_thumbnailImage, data);

            int adapterPos = getAdapterPosition();
            ViewCompat.setTransitionName(_priceTag,"myTransition"+adapterPos);
        }

        private void setUpTitle(TextView tv, Data data) {
            String name = data.getTitle();
//            Log.d("name",name);
            if (!TextUtils.isEmpty(name)) {
                tv.setText(name);
            }
        }

        private void setUpSubtitle(TextView tv, Data data) {
            String price = data.getRent();
            if (!TextUtils.isEmpty(price)) {
                tv.setText("\u20B9 "+price);
            }
            }
        }

        private void setUpFurnishedText(TextView tv, Data data) {
            String formattedDuration = data.getFurnishing();
            if(!TextUtils.isEmpty(formattedDuration))
                tv.setText(formattedDuration);
        }

        private void setUpArea(TextView tv, Data data) {
            String sqft = data.getPropertySize()+" Sq.ft";
            if(!TextUtils.isEmpty(sqft))
                tv.setText(sqft);
        }

    private void setUpThumbnail(ImageView iv, Data data) {

        String imageBaseUrl = "http://d3snwcirvb4r88.cloudfront.net/images/";
        Log.d("image","fetching");
        String objectId = data.getId();
        boolean setThumbnail = true;
        if (data.getPhotos().size()!=0) {
            for (int i = 0; i < data.getPhotos().size() && setThumbnail; i++) {
                setThumbnail =false;
                String thumbnailUrl = imageBaseUrl +objectId+"/"+ data.getPhotos().get(i).getImagesMap().getLarge();
//                Log.d("thumbnail", thumbnailUrl);
                if (!TextUtils.isEmpty(thumbnailUrl)) {
                    Picasso.with(iv.getContext())
                            .load(thumbnailUrl)
//                            .placeholder(R.drawable.rectangle)
//                                .error(R.drawable.ic_error)
                            .into(iv);
                }
            }
        }
    }


    }

