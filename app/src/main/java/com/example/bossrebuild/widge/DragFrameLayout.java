package com.example.bossrebuild.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bossrebuild.util.LogUtils;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/8
 */
public class DragFrameLayout extends View {

    public DragFrameLayout(@NonNull Context context) {
        super(context);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private RectF oval;
    private Paint paint;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("#99CCFF88"));
        oval = new RectF();

        LogUtils.e("---height:"+h);

        oval.set(0,0,w,h);

    }


    float startY = -1;
    float translationY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                LogUtils.e("--startY:"+startY);
                break;

            case MotionEvent.ACTION_MOVE:

                float endY = event.getY();
                float moveY = endY - startY;

                //translationY = translationY + moveY;
                oval.set(oval.left, oval.top, oval.right, oval.bottom+moveY);
                startY = endY;
                LogUtils.e("oval.bottom="+oval.bottom+"--translationY:"+translationY);


                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:

                startY = -1;
                translationY = 0;
                break;
        }



        return true;
    }

    private float realHeight = 0;
    public float getRealHeight(){
        return realHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        realHeight = oval.height();
        LogUtils.e("--onDraw:"+oval.height());
        canvas.drawRect(oval, paint);
    }
}
