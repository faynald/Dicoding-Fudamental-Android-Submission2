package com.farhanrv.submission2githubuser.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farhanrv.submission2githubuser.ui.detail.DetailViewModel;

public class FavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile FavoriteViewModelFactory INSTANCE;
    private final Application mApplication;

    private FavoriteViewModelFactory(Application application) {
        mApplication = application;
    }

    public static FavoriteViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (FavoriteViewModelFactory.class) {
                INSTANCE = new FavoriteViewModelFactory(application);
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class: " + modelClass.getName());
    }
}
