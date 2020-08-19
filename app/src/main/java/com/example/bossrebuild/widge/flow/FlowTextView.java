package com.example.bossrebuild.widge.flow;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.bossrebuild.R;
import com.example.bossrebuild.util.LogUtils;


/**
 * @Description: 点击切换背景的TextView
 * @Author: wujun
 * @Date: 2020/5/20
 */
public class FlowTextView extends AppCompatTextView {


    private boolean isChecked = false;


    public FlowTextView(Context context) {
       super(context);
       init();
    }

    private void init() {
        int padding_v = dip2px(6);
        int padding_h = dip2px(10);
        setTextColor(Color.parseColor("#999999"));
        setGravity(Gravity.CENTER);
        setPadding(padding_h,padding_v,padding_h,padding_h);
        setBackgroundResource(R.mipmap.icon_car_unselected);
        setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!isChecked);
            }
        });
    }

    public void setChecked(boolean checked) {
        if(listener != null){
            listener.onChecked(checked,getText().toString().trim());
        }
        LogUtils.e("--can chg:"+(isChecked != checked));
        if(isChecked != checked){
            changeBackground(checked);
        }
        isChecked = checked;
    }



    private OnCheckedListener listener;
    public interface OnCheckedListener{
        void onChecked(boolean isChecked,String content);
    }
    public void setOnCheckedListener(OnCheckedListener listener){
        this.listener = listener;
    }


    private void changeBackground(boolean isChecked){
        LogUtils.e("--last:"+isChecked);
        this.setBackgroundResource(isChecked? R.mipmap.icon_cart_selected:R.mipmap.icon_car_unselected);
        this.setTextColor(isChecked?Color.parseColor("#ED6F00"):Color.parseColor("#999999"));
    }


    public int dip2px(float dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

}
