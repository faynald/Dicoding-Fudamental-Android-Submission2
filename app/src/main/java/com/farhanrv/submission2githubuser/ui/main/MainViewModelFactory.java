package com.farhanrv.submission2githubuser.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farhanrv.submission2githubuser.helper.SettingPreferences;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SettingPreferences pref;

    public MainViewModelFactory(SettingPreferences pref) {
        this.pref = pref;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(pref);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
