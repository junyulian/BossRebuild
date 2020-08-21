package com.example.bossrebuild.widge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/7
 */
public class ScrollPhoto extends androidx.appcompat.widget.AppCompatImageView {
    public ScrollPhoto(Context context) {
        super(context);
    }

    public ScrollPhoto(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollPhoto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private Bitmap bitmap;
    private boolean isLoaded = false;
    private Matrix matrix;
    private float translationY = 0.0f;

    public void setResourceBitmap(Context context, int imageId){

        Bitmap srcBitmap = BitmapFactory.decodeResource(context.getResources(), imageId);

        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();


        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int newWidth = dm.widthPixels;
        int newHeight = dm.heightPixels;


        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        isLoaded = true;
        matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);

        bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
    }

    public void setResourceBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        isLoaded = true;
        matrix = new Matrix();
        matrix.setScale(2,2);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isLoaded){
            matrix.reset();
            matrix.postTranslate(0, translationY);//中点坐标移动
            canvas.drawBitmap(bitmap, matrix, null);
        }
    }

    float startY = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float endY = event.getY();
                float moveY = endY - startY;

                translationY = translationY + moveY;
                startY = endY;
                invalidate();
                break;
        }

        return true;
    }

}
