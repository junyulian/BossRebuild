package com.example.bossrebuild.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.DialogFragment;

import com.example.bossrebuild.R;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public class BaseDialog extends DialogFragment {


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置window弹框透明，布局文件的圆角才能展示

        window.setWindowAnimations(R.style.dialog_style);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        window.setLayout((int)(dm.widthPixels*0.8), ViewGroup.LayoutParams.WRAP_CONTENT);//设置边距

        setCancelable(false);//默认不可外部点击关闭
    }


}
