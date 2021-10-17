package com.farhanrv.submission2githubuser.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farhanrv.submission2githubuser.Adapter.FollowAdapter;
import com.farhanrv.submission2githubuser.Model.MainViewModel;
import com.farhanrv.submission2githubuser.Model.ModelSearchItem;
import com.farhanrv.submission2githubuser.databinding.FragmentFollowBinding;

public class FragmentFollowers extends Fragment {
    FragmentFollowBinding binding;
    ModelSearchItem modelSearchItem;
    FollowAdapter followAdapter;
    MainViewModel mainViewModel;
    public static final String EXTRA_DETAIL_USER = "modelSearchItem";

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
            modelSearchItem = this.getArguments().getParcelable(EXTRA_DETAIL_USER);
        }
        String username = modelSearchItem.getLogin();

        followAdapter = new FollowAdapter(getContext());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), llm.getOrientation());
        binding.rvFragment.setLayoutManager(llm);
        binding.rvFragment.setAdapter(followAdapter);
        binding.rvFragment.addItemDecoration(divider);
        binding.rvFragment.setHasFixedSize(true);

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.setFollowerUser(username);
        mainViewModel.getFollowing().observe(getViewLifecycleOwner(), modelFollowItems -> followAdapter.setFollowList(modelFollowItems));

        mainViewModel.isLoading().observe(getViewLifecycleOwner(), this::showLoading);
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