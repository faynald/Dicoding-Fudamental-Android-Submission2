package com.farhanrv.submission2githubuser.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farhanrv.submission2githubuser.R;
import com.farhanrv.submission2githubuser.adapter.FollowAdapter;
import com.farhanrv.submission2githubuser.model.ModelUserItem;
import com.farhanrv.submission2githubuser.databinding.FragmentFollowBinding;

public class FragmentFollowers extends Fragment {
    FragmentFollowBinding binding;
    ModelUserItem modelUserItem;
    FollowAdapter followAdapter;
    FollowersViewModel followersViewModel;
    public static final String EXTRA_DETAIL_USER = "modelUserItem";

    public FragmentFollowers() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFollowBinding.inflate(inflater, container, false);

        if (this.getArguments() != null) {
            modelUserItem = this.getArguments().getParcelable(EXTRA_DETAIL_USER);
        }
        String username = modelUserItem.getLogin();

        followAdapter = new FollowAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), llm.getOrientation());
        binding.rvFragment.setLayoutManager(llm);
        binding.rvFragment.setAdapter(followAdapter);
        binding.rvFragment.addItemDecoration(divider);
        binding.rvFragment.setHasFixedSize(true);

        followersViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel.class);
        followersViewModel.setFollowerUser(username);
        followersViewModel.getFollowers().observe(getViewLifecycleOwner(), items -> {
            if (!items.isEmpty()) {
                followAdapter.setFollowList(items);
            } else {
                binding.tvDataNotFound.setText(R.string.no_followers);
                binding.tvDataNotFound.setVisibility(View.VISIBLE);
            }

        });

        followersViewModel.isLoading().observe(getViewLifecycleOwner(), this::showLoading);
        return binding.getRoot();
    }

    private void showLoading(boolean b) {
        if (b) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}