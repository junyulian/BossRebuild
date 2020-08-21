package com.example.bossrebuild.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.bossrebuild.util.LogUtils;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/20
 */
public class MoveAbleView extends View {

    private int mParentHeight;

    public MoveAbleView(Context context) {
        super(context);
    }

    public MoveAbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveAbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ViewGroup parent = (ViewGroup)getParent();
        if(parent != null){
            mParentHeight = parent.getHeight();
        }
    }

    private float startY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                float offsetY = moveY - startY;
                if(getTop() < 0 && offsetY<0)offsetY =0;
                if(getTop() > mParentHeight-getHeight() && offsetY>0)offsetY = 0;
                offsetTopAndBottom((int)offsetY);
                break;
        }
        return true;
    }



    /*
    //private int moveX;
    private int moveY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //moveX = x;
                moveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //int offsetX = x - moveX;
                int offsetY = y - moveY;

                //offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
        }
        return true;
    }

 */
}
