package com.farhanrv.submission2githubuser.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.farhanrv.submission2githubuser.model.ModelUserItem;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ModelUserItem modelUserItem);

    @Query("DELETE FROM ModelUserItem WHERE login = :login")
    void delete(String login);

    @Query("SELECT * FROM ModelUserItem ORDER BY db_id ASC")
    LiveData<List<ModelUserItem>> getAllFavorites();

    @Query("SELECT COUNT(login) FROM ModelUserItem WHERE login = :login")
    LiveData<Boolean> ifExist(String login);
}
