package com.example.bossrebuild.activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityLiveDataBinding;
import com.example.bossrebuild.fragment.LiveDataFragment;
import com.example.bossrebuild.util.LogUtils;

import java.util.Random;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/6
 */
public class LiveDataActivity extends BaseActivity<ActivityLiveDataBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_data;
    }

    public MutableLiveData<String> liveDataA = new MutableLiveData<>();
    public MutableLiveData<String> liveDataB = new MutableLiveData<>();

    private Observer<String> chgObserverA = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            LogUtils.e("a-s:"+s);
            binding.txtLivedataA.setText(s);
        }
    };
    private Observer<String> chgObserverB = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            LogUtils.e("b-s:"+s);
            binding.txtLivedataB.setText(s);
        }
    };


    @Override
    protected void initView() {
        liveDataA.observe(this,chgObserverA);
        liveDataB.observe(this,chgObserverB);

        binding.btnLivedataA.setOnClickListener(v->{
            liveDataA.postValue(new Random().nextInt(9999)+"");
        });
        binding.btnLivedataB.setOnClickListener(v->{
            liveDataB.postValue(new Random().nextInt(9999)+"");
        });

        binding.btnControlFragment.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,new LiveDataFragment())
                    .commit();
        });


    }

    @Override
    protected void initData() {

    }



}
