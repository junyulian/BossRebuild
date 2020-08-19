package com.example.bossrebuild.sysimp;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/19
 */
public class PointSinEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        PointF startPoint = (PointF) startValue;
        PointF endPoint = (PointF) endValue;

        float x = startPoint.x + fraction*(endPoint.x-startPoint.x);
        float y = (float)(Math.sin(x*Math.PI/180)*100)+endPoint.y/2;

        PointF point = new PointF(x,y);

        return point;
    }
}
