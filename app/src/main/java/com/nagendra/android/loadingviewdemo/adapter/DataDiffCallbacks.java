package com.nagendra.android.loadingviewdemo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.nagendra.android.loadingviewdemo.network.models.request.Property;
import com.nagendra.android.loadingviewdemo.network.models.response.Data;

import java.util.List;

/**
 * Created by Nagendra singh on 16/01/18.
 * FRSLabs Ltd
 * Nagendra@frslabs.com
 */

public class DataDiffCallbacks extends DiffUtil.Callback {
    private final List<Property> oldList;
    private final List<Property> newList;
    protected DataDiffCallbacks(List<Property> oldList, List<Property> newList) {
        super();
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getPropertyName() == newList.get(newItemPosition).getPropertyName();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Property oldItem = oldList.get(oldItemPosition);
        final Property newItem = newList.get(newItemPosition);

        return oldItem.getPropertyName().equals(newItem.getPropertyName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
