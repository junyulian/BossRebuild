package com.example.bossrebuild.activity;


import android.util.Log;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityDialogBinding;
import com.example.bossrebuild.dialog.CommonConfirmDialog;
import com.example.bossrebuild.dialog.DialogInterface;


/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public class DialogActivity extends BaseActivity<ActivityDialogBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initView() {
        binding.tvConfirm.setOnClickListener(v->showConfirmDialog());
    }


    private void showConfirmDialog() {

        new CommonConfirmDialog.Builder()
                .setMsg("您确认离开这个页面吗？")
                .setLeftBtn("是")
                .setLeftBtnClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(View view, DialogFragment dialog) {
                        Log.e("----","---NO-LEFT");
                        dialog.dismiss();
                    }
                })
                .setRightBtn("不是")
                .setRightBtnClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(View view,DialogFragment dialog) {
                        dialog.dismiss();
                    }
                })
                .show(getSupportFragmentManager(),"confirm");

    }

    @Override
    protected void initData() {

    }
}
