package com.farhanrv.submission2githubuser.UI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.farhanrv.submission2githubuser.Adapter.SectionsPagerAdapter;
import com.farhanrv.submission2githubuser.Model.MainViewModel;
import com.farhanrv.submission2githubuser.Model.ModelSearchItem;
import com.farhanrv.submission2githubuser.databinding.ActivityDetailBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    public static final String EXTRA_DETAIL_USER = "extra_detail_user";
    ModelSearchItem modelSearchItem;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] strObject = {"Following", "Followers"};

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelSearchItem = getIntent().getParcelableExtra(EXTRA_DETAIL_USER);

        binding.viewPager.setAdapter(new SectionsPagerAdapter(this, modelSearchItem));
        binding.viewPager.setOffscreenPageLimit(2);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(strObject[position])
        ).attach();

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setUserDetail(modelSearchItem.getLogin());

        mainViewModel.getUser().observe(this, modelUser -> {
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

        mainViewModel.isLoading().observe(this, this::showLoading);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoading(boolean b) {
        if (b) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}