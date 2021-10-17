package com.farhanrv.submission2githubuser.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farhanrv.submission2githubuser.Networking.ApiConfig;
import com.farhanrv.submission2githubuser.Networking.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final MutableLiveData<ArrayList<ModelSearchItem>> modelSearchMLD = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<ModelFollowItem>> modelFollowItemMLD = new MutableLiveData<>();
    private final MutableLiveData<ModelUser> modelUserMLD = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<ArrayList<ModelSearchItem>> getSearchResult() {
        return modelSearchMLD;
    }

    public LiveData<ArrayList<ModelFollowItem>> getFollowing() {
        return modelFollowItemMLD;
    }

    public LiveData<ModelUser> getUser() {
        return modelUserMLD;
    }

    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void findUser(String keyword) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ModelSearchResponse> client = apiService.searchUser(keyword);
        client.enqueue(new Callback<ModelSearchResponse>() {
            @Override
            public void onResponse(Call<ModelSearchResponse> call, Response<ModelSearchResponse> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ArrayList<ModelSearchItem> items = new ArrayList<>(response.body().getItems());
                        modelSearchMLD.postValue(items);
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelSearchResponse> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void setUserDetail(String username) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ModelUser> modelUserCall = apiService.getDetailUser(username);
        modelUserCall.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        modelUserMLD.setValue(response.body());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void setFollowingUser(String username) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ArrayList<ModelFollowItem>> modelFollowItemCall = apiService.getFollowingUser(username);
        modelFollowItemCall.enqueue(new Callback<ArrayList<ModelFollowItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollowItem>> call, Response<ArrayList<ModelFollowItem>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        modelFollowItemMLD.setValue(response.body());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollowItem>> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void setFollowerUser(String username) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ArrayList<ModelFollowItem>> modelFollowItemCall = apiService.getFollowerUser(username);
        modelFollowItemCall.enqueue(new Callback<ArrayList<ModelFollowItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollowItem>> call, Response<ArrayList<ModelFollowItem>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        modelFollowItemMLD.setValue(response.body());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "incomplete result: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollowItem>> call, Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}