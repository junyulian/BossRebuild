package com.example.bossrebuild.dialog;

import android.view.View;

import androidx.fragment.app.DialogFragment;


/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public class CommonConfirmController {

    private DialogInterface.OnClickListener mLeftButtonListener;
    private DialogInterface.OnClickListener mRightButtonListener;
    private DialogFragment dialog;

    public void setLeftButton(View view){

        if(mLeftButtonListener != null){
            view.setOnClickListener(v->mLeftButtonListener.onClick(view,dialog));
        }
    }

    public void setRightButton(View view){
        if(mRightButtonListener != null){
            view.setOnClickListener(v->mRightButtonListener.onClick(view,dialog));
        }
    }

    public void setOnLeftClickListener(DialogInterface.OnClickListener listener,DialogFragment dialog){
        mLeftButtonListener = listener;
        this.dialog = dialog;
    }

    public void setOnRightClickListener(DialogInterface.OnClickListener listener,DialogFragment dialog){
        mRightButtonListener = listener;
        this.dialog = dialog;
    }

    public static class Params {
        public DialogInterface.OnClickListener mLeftButtonListener;
        public DialogInterface.OnClickListener mRightButtonListener;


        public void apply(CommonConfirmController controller, DialogFragment dialog) {
            if(mLeftButtonListener != null){
                controller.setOnLeftClickListener(mLeftButtonListener,dialog);
            }
            if(mRightButtonListener != null){
                controller.setOnRightClickListener(mRightButtonListener,dialog);
            }
        }
    }
}
