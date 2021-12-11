package com.farhanrv.submission2githubuser.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.farhanrv.submission2githubuser.database.FavoriteDao;
import com.farhanrv.submission2githubuser.database.FavoriteRoomDatabase;
import com.farhanrv.submission2githubuser.model.ModelUserItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteRepository {

    private final FavoriteDao mFavoriteDao;
    private final ExecutorService executorService;

    public FavoriteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        FavoriteRoomDatabase db = FavoriteRoomDatabase.getDatabase(application);
        mFavoriteDao = db.favoriteDao();
    }

    public LiveData<List<ModelUserItem>> getAllFavorites() {
        return mFavoriteDao.getAllFavorites();
    }

    public LiveData<Boolean> ifExist(final String login) {
        return mFavoriteDao.ifExist(login);
    }

    public void insert(final ModelUserItem modelUserItem) {
        executorService.execute(() -> mFavoriteDao.insert(modelUserItem));
    }

    public void delete(final String login) {
        executorService.execute(() -> mFavoriteDao.delete(login));
    }
}
