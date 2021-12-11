package com.farhanrv.submission2githubuser.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farhanrv.submission2githubuser.helper.SettingPreferences;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.model.ModelUserResponse;
import com.farhanrv.submission2githubuser.networking.ApiConfig;
import com.farhanrv.submission2githubuser.networking.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final SettingPreferences pref;
    private static final String TAG = "MainViewModel";

    private final MutableLiveData<ArrayList<ModelUserItem>> modelSearchMLD = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public MainViewModel(SettingPreferences pref) {
        this.pref = pref;
    }

    public LiveData<Boolean> getThemeSetting() {
        return LiveDataReactiveStreams.fromPublisher(pref.getThemeSetting());
    }

    public void saveThemeSetting(Boolean isDarkModeActive) {
        pref.saveThemeSetting(isDarkModeActive);
    }

    public LiveData<ArrayList<ModelUserItem>> getSearchResult() {
        return modelSearchMLD;
    }

    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void findUser(String keyword) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ModelUserResponse> client = apiService.searchUser(keyword);
        client.enqueue(new Callback<ModelUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<ModelUserResponse> call, @NonNull Response<ModelUserResponse> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ArrayList<ModelUserItem> items = new ArrayList<>(response.body().getItems());
                        modelSearchMLD.postValue(items);
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelUserResponse> call, @NonNull Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }



}