package com.example.bossrebuild.widge;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/19
 */
public class PointAnimView extends View {

    public static final float RADIUS = 20f;
    private PointF currentPoint;

    private Paint mPaint;
    private Paint linePaint;

    private AnimatorSet animSet;
    private TimeInterpolator interpolatorType = new LinearInterpolator();

    private int color;
    private float radius = RADIUS;


    public PointAnimView(Context context) {
        super(context);
        init();
    }

    public PointAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
        mPaint.setColor(this.color);
    }

    public float getRadius(){
        return radius;
    }

    public void setRadius(){
        this.radius = radius;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.TRANSPARENT);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(currentPoint == null){
            currentPoint = new PointF(RADIUS,RADIUS);
        }
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {



    }
}
