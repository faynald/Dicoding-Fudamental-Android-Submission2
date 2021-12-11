package com.farhanrv.submission2githubuser.ui.detail;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farhanrv.submission2githubuser.model.ModelFollowItem;
import com.farhanrv.submission2githubuser.networking.ApiConfig;
import com.farhanrv.submission2githubuser.networking.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingViewModel extends ViewModel {

    private static final String TAG = "FollowersViewModel";
    private final MutableLiveData<ArrayList<ModelFollowItem>> following = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<ArrayList<ModelFollowItem>> getFollowing() {
        return following;
    }

    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void setFollowingUser(String username) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ArrayList<ModelFollowItem>> modelFollowItemCall = apiService.getFollowingUser(username);
        modelFollowItemCall.enqueue(new Callback<ArrayList<ModelFollowItem>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ModelFollowItem>> call, @NonNull Response<ArrayList<ModelFollowItem>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        following.setValue(response.body());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ModelFollowItem>> call, @NonNull Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
