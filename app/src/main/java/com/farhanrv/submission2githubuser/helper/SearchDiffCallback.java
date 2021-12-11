package com.farhanrv.submission2githubuser.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.farhanrv.submission2githubuser.model.ModelUserItem;

import java.util.List;

public class SearchDiffCallback extends DiffUtil.Callback {
    private final List<ModelUserItem> mOldSearchList;
    private final List<ModelUserItem> mNewSearchList;

    public SearchDiffCallback(List<ModelUserItem> mOldSearchList, List<ModelUserItem> mNewSearchList) {
        this.mOldSearchList = mOldSearchList;
        this.mNewSearchList = mNewSearchList;
    }

    @Override
    public int getOldListSize() {
        return mOldSearchList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewSearchList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldSearchList.get(oldItemPosition).getLogin().equals(mNewSearchList.get(newItemPosition).getLogin());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ModelUserItem oldSearchItem = mOldSearchList.get(oldItemPosition);
        final ModelUserItem newSearchItem = mNewSearchList.get(newItemPosition);

        return oldSearchItem.getLogin().equals(newSearchItem.getLogin()) && oldSearchItem.getAvatarUrl().equals(newSearchItem.getAvatarUrl());
    }
}
