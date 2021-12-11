package com.farhanrv.submission2githubuser.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.farhanrv.submission2githubuser.R;
import com.farhanrv.submission2githubuser.databinding.ActivitySplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    Animation fadeInAnim;
    private final Handler handler = new Handler(Looper.getMainLooper());
    int delayTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        binding.tvSubmissionTitle.setAnimation(fadeInAnim);
        binding.imgGithub.setAnimation(fadeInAnim);

        Runnable workRunnable = () -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        };
        handler.postDelayed(workRunnable, delayTime);
    }
}