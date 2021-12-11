package com.farhanrv.submission2githubuser.ui.favorite;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.repository.FavoriteRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {

    private final FavoriteRepository mFavoriteRepository;

    public FavoriteViewModel(Application application) {
        mFavoriteRepository = new FavoriteRepository(application);
    }

    public LiveData<List<ModelUserItem>> getAllFavorites() {
        return mFavoriteRepository.getAllFavorites();
    }
}
