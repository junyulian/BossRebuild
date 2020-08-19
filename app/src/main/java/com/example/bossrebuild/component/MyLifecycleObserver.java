package com.example.bossrebuild.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;


import com.example.bossrebuild.util.LogUtils;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/6
 */
public class MyLifecycleObserver implements LifecycleEventObserver {


    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        LogUtils.e("--state:"+source.getClass().getSimpleName()+"--event:"+event.name());

    }

}
