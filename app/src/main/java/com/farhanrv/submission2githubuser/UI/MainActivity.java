package com.farhanrv.submission2githubuser.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farhanrv.submission2githubuser.Adapter.SearchAdapter;
import com.farhanrv.submission2githubuser.Model.MainViewModel;
import com.farhanrv.submission2githubuser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        mainViewModel.isLoading().observe(this, this::showLoading);

        binding.tietSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            String strKeyword = binding.tietSearch.getText().toString();
            if (strKeyword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Tidak boleh kosong !", Toast.LENGTH_SHORT).show();
            } else {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    mainViewModel.findUser(strKeyword);
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    return true;
                }
            }
            return false;
        });

        binding.tietSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            final Handler handler = new Handler(Looper.getMainLooper());
            Runnable workRunnable;

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacks(workRunnable);
                workRunnable = () -> {
                    String strKeyword = binding.tietSearch.getText().toString();
                    if (strKeyword.isEmpty()) {
                        binding.rvHome.setVisibility(View.INVISIBLE);
                    } else {
                        mainViewModel.findUser(strKeyword);
                    }
                };
                handler.postDelayed(workRunnable, 500 );
            }
        });

        SearchAdapter searchAdapter = new SearchAdapter(this);
        binding.rvHome.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHome.setAdapter(searchAdapter);
        binding.rvHome.setHasFixedSize(true);

        mainViewModel.getSearchResult().observe(this, modelSearchItems -> {
            if (modelSearchItems.size() != 0) {
                binding.tvTidakDitemukan.setVisibility(View.INVISIBLE);
                binding.rvHome.setVisibility(View.VISIBLE);
                searchAdapter.setModelSearchItemArrayList(modelSearchItems);
            } else {
                binding.rvHome.setVisibility(View.INVISIBLE);
                binding.tvTidakDitemukan.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Pengguna Tidak Ditemukan :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showLoading(boolean b) {
        if (b) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}