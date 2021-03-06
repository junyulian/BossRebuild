package com.example.bossrebuild.widge;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.example.bossrebuild.sysimp.PointSinEvaluator;

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
        mPaint.setColor(Color.RED);

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
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {

        canvas.drawLine(10,getHeight()/2,getWidth(),getHeight()/2,linePaint);
        canvas.drawLine(10,getHeight()/2-150,10,getHeight()/2+150,linePaint);
        canvas.drawPoint(currentPoint.x,currentPoint.y,linePaint);

    }

    private void drawCircle(Canvas canvas) {

        float x = currentPoint.x;
        float y = currentPoint.y;

        canvas.drawCircle(x,y,radius,mPaint);

    }



    public void startAnimation(){
        PointF startP = new PointF(RADIUS,RADIUS);
        PointF endP = new PointF(getWidth()-RADIUS, getHeight()-RADIUS);
        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointSinEvaluator(), startP, endP);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (PointF) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();


        ObjectAnimator animColor = ObjectAnimator.ofObject(this, "color", new ArgbEvaluator(), Color.GREEN,
                Color.YELLOW, Color.BLUE, Color.WHITE, Color.RED);
        animColor.setRepeatCount(-1);
        animColor.setRepeatMode(ValueAnimator.REVERSE);


        ValueAnimator animScale = ValueAnimator.ofFloat(20f, 80f, 60f, 10f, 35f,55f,10f);
        animScale.setRepeatCount(-1);
        animScale.setRepeatMode(ValueAnimator.REVERSE);
        animScale.setDuration(5000);
        animScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (float) animation.getAnimatedValue();
            }
        });


        animSet = new AnimatorSet();
        animSet.play(valueAnimator).with(animColor).with(animScale);
        animSet.setDuration(5000);
        animSet.setInterpolator(interpolatorType);
        animSet.start();

    }

    public void setInterpolatorType(int type ) {
        switch (type) {
            case 1:
                interpolatorType = new BounceInterpolator();
                break;
            case 2:
                interpolatorType = new AccelerateDecelerateInterpolator();
                break;
            case 3:
                interpolatorType = new DecelerateInterpolator();
                break;
            case 4:
                interpolatorType = new AnticipateInterpolator();
                break;
            case 5:
                interpolatorType = new LinearInterpolator();
                break;
            case 6:
                interpolatorType=new LinearOutSlowInInterpolator();
                break;
            case 7:
                interpolatorType = new OvershootInterpolator();
            default:
                interpolatorType = new LinearInterpolator();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void pauseAnimation() {
        if (animSet != null) {
            animSet.pause();
        }
    }

    public void stopAnimation() {
        if (animSet != null) {
            animSet.cancel();
            this.clearAnimation();
        }
    }

}
