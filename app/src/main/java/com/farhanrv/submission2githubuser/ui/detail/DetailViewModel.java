package com.farhanrv.submission2githubuser.ui.detail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.farhanrv.submission2githubuser.model.ModelUserDetail;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.networking.ApiConfig;
import com.farhanrv.submission2githubuser.networking.ApiService;
import com.farhanrv.submission2githubuser.repository.FavoriteRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    private static final String TAG = "DetailViewModel";

    private final FavoriteRepository mFavoriteRepository;

    public DetailViewModel(Application application) {
        mFavoriteRepository = new FavoriteRepository(application);
    }

    public LiveData<Boolean> ifExist(String login) {
        return mFavoriteRepository.ifExist(login);
    }

    public void insert(ModelUserItem modelUserItem) {
        mFavoriteRepository.insert(modelUserItem);
    }

    public void delete(String login) {
        mFavoriteRepository.delete(login);
    }

    private final MutableLiveData<ModelUserDetail> modelUserMLD = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<ModelUserDetail> getUser() {
        return modelUserMLD;
    }

    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void setUserDetail(String username) {
        _isLoading.setValue(true);
        ApiService apiService = ApiConfig.getApiService();
        Call<ModelUserDetail> modelUserCall = apiService.getDetailUser(username);
        modelUserCall.enqueue(new Callback<ModelUserDetail>() {
            @Override
            public void onResponse(@NonNull Call<ModelUserDetail> call, @NonNull Response<ModelUserDetail> response) {
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
            public void onFailure(@NonNull Call<ModelUserDetail> call, @NonNull Throwable t) {
                _isLoading.setValue(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
