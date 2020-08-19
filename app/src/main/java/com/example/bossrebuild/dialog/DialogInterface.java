package com.example.bossrebuild.dialog;

import android.view.View;

import androidx.fragment.app.DialogFragment;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public interface DialogInterface {
    interface OnCancelListener {
        void onCancel(DialogInterface dialog);
    }
    interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }
    interface OnShowListener {
        void onShow(DialogInterface dialog);
    }
    interface OnClickListener {
        void onClick(View view, DialogFragment dialog);
        //void onClick(View view);
        //void onClick(DialogInterface dialog, int which);
    }
}
