package com.example.bossrebuild.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.view.View;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityAnimatorBinding;
import com.example.bossrebuild.sysimp.PointSinEvaluator;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/19
 */
public class AnimatorActivity extends BaseActivity<ActivityAnimatorBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_animator;
    }

    @Override
    protected void initView() {

        binding.btnRotate.setOnClickListener(v -> rotate(binding.view));
        binding.btnAlpha.setOnClickListener(v -> alpha(binding.view));
        binding.btnGroup.setOnClickListener(v -> group(binding.view));
        binding.btnSin.setOnClickListener(v -> sinMove(binding.view));
    }

    @Override
    protected void initData() {

    }


    private void sinMove(View view){
        Point startP = new Point((int)view.getX(), (int)view.getY());//初始值（起点）
        Point endP = new Point(view.getWidth() , view.getHeight());//结束值（终点）
        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointSinEvaluator(), startP, endP);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //currentPoint = (Point) animation.getAnimatedValue();
                view.postInvalidate();
            }
        });
        valueAnimator.start();
    }


    private void rotate(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0f,360f);
        animator.setDuration(3000);
        animator.start();
    }


    private void alpha(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"alpha",1.0f,0.8f,0.6f,0.4f,0.2f,0.0f);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setDuration(3000);
        animator.start();
    }

    private void group(View view){
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f, 0.8f, 1.0f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 2.0f);
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(view, "translationX", 100, 400);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", 100, 750);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim,scaleXAnim,scaleYAnim,rotateAnim,transXAnim,transYAnim);

        set.setDuration(5000);
        set.start();
    }

}
