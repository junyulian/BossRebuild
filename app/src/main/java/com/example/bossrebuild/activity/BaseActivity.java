package com.example.bossrebuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.bossrebuild.component.MyLifecycleObserver;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public abstract class BaseActivity<E extends ViewDataBinding> extends AppCompatActivity {

    protected E binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycle().addObserver(new MyLifecycleObserver());


        (getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();// finish your activity
        }
        return super.onOptionsItemSelected(item);
    }


    public<T> void startActivity(Class<T> t){
        Intent intent = new Intent(this,t);
        startActivity(intent);
    }
}
