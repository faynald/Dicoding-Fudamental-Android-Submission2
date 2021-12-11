package com.farhanrv.submission2githubuser.ui.favorite;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farhanrv.submission2githubuser.adapter.FavoriteAdapter;
import com.farhanrv.submission2githubuser.databinding.ActivityFavoriteBinding;
import com.farhanrv.submission2githubuser.model.ModelUserItem;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private ActivityFavoriteBinding binding;
    private FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Favorite user");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FavoriteViewModel viewModel = obtainViewModel(FavoriteActivity.this);
        viewModel.getAllFavorites().observe(this, favorite -> {
            if (!favorite.isEmpty()) {
                binding.emptyFavorite.setVisibility(View.GONE);
                ArrayList<ModelUserItem> favoriteArrayList = new ArrayList<>(favorite);
                adapter.setListUser(favoriteArrayList);
            } else {
                binding.mainLinearLayout.setVisibility(View.GONE);
                binding.emptyFavorite.setVisibility(View.VISIBLE);
            }
        });

        adapter = new FavoriteAdapter(this);
        binding.rvListFavoriteUser.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListFavoriteUser.setHasFixedSize(true);
        binding.rvListFavoriteUser.setAdapter(adapter);
        binding.rvListFavoriteUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @NonNull
    private FavoriteViewModel obtainViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory factory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(FavoriteViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}