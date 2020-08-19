package com.example.bossrebuild.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/6
 */
public abstract class BaseFragment<E extends ViewDataBinding> extends Fragment {


    protected E binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

        View rootView = binding.getRoot();

        return rootView;
    }

    protected  abstract  int getLayoutId();
}
