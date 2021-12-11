package com.farhanrv.submission2githubuser.ui.detail;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.R;
import com.farhanrv.submission2githubuser.adapter.SectionsPagerAdapter;
import com.farhanrv.submission2githubuser.ui.favorite.FavoriteViewModelFactory;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.databinding.ActivityDetailBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    public static final String EXTRA_DETAIL_USER = "extra_detail_user";
    ModelUserItem modelUserItem;
    DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Resources res = getResources();
        final String[] strObject = res.getStringArray(R.array.tab_follow);

        detailViewModel = obtainViewModel(DetailActivity.this);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelUserItem = getIntent().getParcelableExtra(EXTRA_DETAIL_USER);

        binding.viewPager.setAdapter(new SectionsPagerAdapter(this, modelUserItem));
        binding.viewPager.setOffscreenPageLimit(2);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(strObject[position])
        ).attach();

        detailViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);
        detailViewModel.setUserDetail(modelUserItem.getLogin());
        detailViewModel.getUser().observe(this, modelUser -> {
            getSupportActionBar().setTitle(modelUser.getLogin());
            Glide.with(binding.getRoot())
                    .load(modelUser.getAvatarUrl())
                    .into(binding.imgDetailPhoto);
            binding.tvDetailName.setText(modelUser.getName());
            binding.tvDetailUsername.setText(modelUser.getLogin());
            if (modelUser.getLocation() != null) {
                binding.tvDetailLocation.setVisibility(View.VISIBLE);
                binding.tvDetailLocation.setText(modelUser.getLocation());
            }
            if (modelUser.getCompany() != null) {
                binding.tvDetailCompany.setVisibility(View.VISIBLE);
                binding.tvDetailCompany.setText(modelUser.getCompany());
            }
            binding.tvDetailFollowers.setText(String.valueOf(modelUser.getFollowers()));
            binding.tvDetailFollowing.setText(String.valueOf(modelUser.getFollowing()));
            binding.tvDetailRepository.setText(String.valueOf(modelUser.getPublicRepos()));
        });
        detailViewModel.ifExist(modelUserItem.getLogin()).observe(this, this::ifFavoriteExist);
        detailViewModel.isLoading().observe(this, this::showLoading);
    }

    @NonNull
    private DetailViewModel obtainViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory factory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(DetailViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showLoading(boolean isLoading) {
        binding.progressBar.setVisibility(
                isLoading ? View.VISIBLE : View.INVISIBLE
        );
    }

    private void ifFavoriteExist(Boolean isExist) {
        if (isExist) {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite);
            binding.fabFavorite.setOnClickListener(view -> {
                detailViewModel.delete(modelUserItem.getLogin());
                showToast(getString(R.string.favorite_removed));
            });
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_favorite_null);
            binding.fabFavorite.setOnClickListener(view -> {
                detailViewModel.insert(modelUserItem);
                showToast(getString(R.string.favorite_added));
            });
        }
    }
}