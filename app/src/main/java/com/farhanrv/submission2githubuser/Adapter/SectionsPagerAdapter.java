package com.farhanrv.submission2githubuser.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.farhanrv.submission2githubuser.Model.ModelSearchItem;
import com.farhanrv.submission2githubuser.UI.FragmentFollowers;
import com.farhanrv.submission2githubuser.UI.FragmentFollowing;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    ModelSearchItem modelSearchItem;

    public SectionsPagerAdapter(AppCompatActivity activity, ModelSearchItem modelSearchItem) {
        super(activity);
        this.modelSearchItem = modelSearchItem;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchItem", modelSearchItem);
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
