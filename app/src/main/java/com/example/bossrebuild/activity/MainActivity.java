package com.example.bossrebuild.activity;

import android.Manifest;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityMainBinding;

import java.util.Queue;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {

        binding.tvFlow.setOnClickListener(v->startActivity(FlowActivity.class));
        binding.tvDialog.setOnClickListener(v->startActivity(DialogActivity.class));
        binding.tvLiveData.setOnClickListener(v->startActivity(LiveDataActivity.class));
        binding.tvPlay.setOnClickListener(v->startActivity(VideoActivity.class));
        binding.tvImg.setOnClickListener(v->startActivity(PhotoImgActivity.class));
        binding.tvAnimator.setOnClickListener(v -> startActivity(AnimatorActivity.class));
        binding.tvMove.setOnClickListener(v -> startActivity(MoveAbleActivity.class));
        binding.tvIdentify.setOnClickListener(v -> startActivity(IdentifyActivity.class));


    }



    @Override
    protected void initData() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermissions(permissions, 10);


    }




}