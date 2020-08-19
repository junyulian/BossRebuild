package com.example.bossrebuild.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.bossrebuild.R;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public class CommonConfirmDialog extends BaseDialog implements DialogInterface{

    private static final String MESSAGE = "message";
    private static final String LEFT_TXT = "left_text";
    private static final String RIGHT_TXT = "right_text";


    private TextView tv_msg;
    private Button btn_left;
    private Button btn_right;

    public CommonConfirmController mAlert =  new CommonConfirmController();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_common_confirm,null);
        tv_msg = view.findViewById(R.id.tv_msg);
        btn_left = view.findViewById(R.id.btn_left);
        btn_right = view.findViewById(R.id.btn_right);
        Bundle bundle = getArguments();//接收给弹框设置的值
        if(bundle != null){
            String msg = bundle.getString(MESSAGE);
            if(!TextUtils.isEmpty(msg)){
                tv_msg.setText(msg);
            }
            String leftTxt = bundle.getString(LEFT_TXT);
            if(!TextUtils.isEmpty(leftTxt)){
                btn_left.setText(leftTxt);
            }
            String rightTxt = bundle.getString(RIGHT_TXT);
            if(!TextUtils.isEmpty(rightTxt)){
                btn_right.setText(rightTxt);
            }
        }
        mAlert.setLeftButton(btn_left);
        mAlert.setRightButton(btn_right);
        return view;
    }


    public static class Builder {
        private Bundle bundle;
        private final CommonConfirmController.Params p;


        public Builder(){
            bundle = new Bundle();
            p = new CommonConfirmController.Params();
        }

        public Builder setMsg(String msg){
            bundle.putString(MESSAGE,msg);
            return this;
        }

        public Builder setLeftBtn(String txt){
            bundle.putString(LEFT_TXT,txt);
            return this;
        }

        public Builder setRightBtn(String txt){
            bundle.putString(RIGHT_TXT,txt);
            return this;
        }

        public Builder setRightBtnClickListener(DialogInterface.OnClickListener listener){
            p.mRightButtonListener = listener;
            return this;
        }

        public Builder setLeftBtnClickListener(DialogInterface.OnClickListener listener){
            p.mLeftButtonListener = listener;
            return this;
        }

        public CommonConfirmDialog create(){
            CommonConfirmDialog dialog = new CommonConfirmDialog();
            dialog.setArguments(bundle);
            return dialog;
        }

        public CommonConfirmDialog show(FragmentManager fragmentManager, String tag){
            final CommonConfirmDialog dialog = create();
            dialog.show(fragmentManager,tag);
            p.apply(dialog.mAlert,dialog);
            return dialog;
        }

    }

}
