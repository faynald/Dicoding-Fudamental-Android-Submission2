package com.farhanrv.submission2githubuser.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.ui.detail.FragmentFollowers;
import com.farhanrv.submission2githubuser.ui.detail.FragmentFollowing;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    ModelUserItem modelUserItem;

    public SectionsPagerAdapter(AppCompatActivity activity, ModelUserItem modelUserItem) {
        super(activity);
        this.modelUserItem = modelUserItem;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelUserItem", modelUserItem);
        Fragment fragment = new FragmentFollowing();
        switch (position) {
            case 0:
                fragment = new FragmentFollowing();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentFollowers();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
