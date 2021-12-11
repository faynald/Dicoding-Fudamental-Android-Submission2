package com.farhanrv.submission2githubuser.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farhanrv.submission2githubuser.R;
import com.farhanrv.submission2githubuser.adapter.SearchAdapter;
import com.farhanrv.submission2githubuser.databinding.ActivityMainBinding;
import com.farhanrv.submission2githubuser.helper.SettingPreferences;
import com.farhanrv.submission2githubuser.ui.favorite.FavoriteActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private Runnable workRunnable;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Search");
        }

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "settings").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);

        mainViewModel = new ViewModelProvider(this, new MainViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSetting().observe(this, isDarkModeActive -> {
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        mainViewModel.isLoading().observe(this, this::showLoading);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                mainViewModel.findUser(text);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                handler.removeCallbacks(workRunnable);
                workRunnable = () -> {
                    mainViewModel.findUser(text);
                    if (text.isEmpty()) {
                        binding.rvHome.setVisibility(View.INVISIBLE);
                    } else {
                        binding.rvHome.setVisibility(View.VISIBLE);
                    }
                };
                handler.postDelayed(workRunnable, 1000);
                return true;
            }
        });

        SearchAdapter searchAdapter = new SearchAdapter(this);
        binding.rvHome.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHome.setAdapter(searchAdapter);
        binding.rvHome.setHasFixedSize(true);
        binding.rvHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mainViewModel.getSearchResult().observe(this, modelSearchItems -> {
            searchAdapter.setSearchList(modelSearchItems);
            if (modelSearchItems.size() == 0) {
                binding.tvUserNotFound.setVisibility(View.VISIBLE);
            } else {
                binding.tvUserNotFound.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_dark_mode) {
            setDarkMode(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO);
        } else if (item.getItemId() == R.id.menu_item_favorite) {
            startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoading(boolean isLoading) {
        binding.progressBar.setVisibility(
                isLoading ? View.VISIBLE : View.INVISIBLE
        );
    }

    private void setDarkMode(boolean isDarkModeActive) {
        mainViewModel.saveThemeSetting(isDarkModeActive);
    }
}