package com.farhanrv.submission2githubuser.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.farhanrv.submission2githubuser.model.ModelFollowItem;

import java.util.List;

public class FollowDiffCallback extends DiffUtil.Callback {
    private final List<ModelFollowItem> mOldFollowList;
    private final List<ModelFollowItem> mNewFollowList;

    public FollowDiffCallback(List<ModelFollowItem> mOldFollowList, List<ModelFollowItem> mNewFollowList) {
        this.mOldFollowList = mOldFollowList;
        this.mNewFollowList = mNewFollowList;
    }

    @Override
    public int getOldListSize() {
        return mOldFollowList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewFollowList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldFollowList.get(oldItemPosition).getId() == mNewFollowList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ModelFollowItem oldFollow = mOldFollowList.get(oldItemPosition);
        final ModelFollowItem newFollow = mNewFollowList.get(newItemPosition);

        return oldFollow.getLogin().equals(newFollow.getLogin()) && oldFollow.getAvatarUrl().equals(newFollow.getAvatarUrl());
    }
}
