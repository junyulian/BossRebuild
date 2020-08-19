package com.example.bossrebuild.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.bossrebuild.R;
import com.example.bossrebuild.activity.LiveDataActivity;
import com.example.bossrebuild.databinding.FragmentLiveDataBinding;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/6
 */
public class LiveDataFragment extends BaseFragment<FragmentLiveDataBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_data;
    }


    private Observer<String> chgObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            binding.txtFragment.setText(s);
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();
        LiveDataActivity activity = (LiveDataActivity) getActivity();

        mediatorLiveData.addSource(activity.liveDataA,chgObserver);
        mediatorLiveData.addSource(activity.liveDataB,chgObserver);

        mediatorLiveData.observe(this,chgObserver);

    }
}