package com.farhanrv.submission2githubuser.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.farhanrv.submission2githubuser.model.ModelUserItem;

import java.util.List;

public class FavoriteDiffCallback extends DiffUtil.Callback {
    private final List<ModelUserItem> mOldFavoriteList;
    private final List<ModelUserItem> mNewFavoriteList;

    public FavoriteDiffCallback(List<ModelUserItem> mOldFavoriteList, List<ModelUserItem> mNewFavoriteList) {
        this.mOldFavoriteList = mOldFavoriteList;
        this.mNewFavoriteList = mNewFavoriteList;
    }

    @Override
    public int getOldListSize() {
        return mOldFavoriteList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewFavoriteList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldFavoriteList.get(oldItemPosition).getDbId() == mNewFavoriteList.get(newItemPosition).getDbId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ModelUserItem oldFavorite = mOldFavoriteList.get(oldItemPosition);
        final ModelUserItem newFavorite = mNewFavoriteList.get(newItemPosition);

        return oldFavorite.getLogin().equals(newFavorite.getLogin()) && oldFavorite.getAvatarUrl().equals(newFavorite.getAvatarUrl());
    }
}
