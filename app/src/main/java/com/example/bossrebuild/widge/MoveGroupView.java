package com.example.bossrebuild.widge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bossrebuild.R;
import com.example.bossrebuild.util.LogUtils;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/20
 */
public class MoveGroupView extends RelativeLayout {

    private int mParentHeight;
    private ImageView iv_v_drag;
    private int iv_left;
    private int iv_top;
    private int iv_right;
    private int iv_bottom;
    private View main_area;

    public MoveGroupView(Context context) {
        super(context);
        init(context);
    }

    public MoveGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MoveGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.move_group_view, this);//绑定view
        Button btn_idy = findViewById(R.id.btn_idy);
        iv_v_drag = findViewById(R.id.iv_v_drag);
        main_area = findViewById(R.id.main_area);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        LogUtils.e("---onLayoutChg");

        iv_left = iv_v_drag.getLeft();
        iv_top = iv_v_drag.getTop();
        iv_right = iv_v_drag.getRight();
        iv_bottom = iv_v_drag.getBottom();



    }

    private RectF rectF;
    private Paint paint;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ViewGroup parent = (ViewGroup)getParent();
        if(parent != null){
            mParentHeight = parent.getHeight();
        }

        //长方形需要重新绘制
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("#FFCCAA"));
        rectF = new RectF();
        rectF.set(0,0,w,h);




    }





    private float startY;
    private boolean is_drag = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                if(startY > iv_top && startY < iv_bottom && event.getX() > iv_left && event.getX() < iv_right){
                    is_drag = true;
                }else {
                    is_drag = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                float offsetY = moveY - startY;
                if(is_drag){
                    RelativeLayout.LayoutParams params = ( RelativeLayout.LayoutParams)main_area.getLayoutParams();
                    params.height = params.height + (int)offsetY;

                    main_area.setLayoutParams(params);

                    startY = moveY;
                }else {
                    if(getTop() < 0 && offsetY<0)offsetY =0;
                    if(getTop() > mParentHeight-getHeight() && offsetY>0)offsetY = 0;
                    offsetTopAndBottom((int)offsetY);
                }
                break;
            case MotionEvent.ACTION_UP:
                is_drag = false;
                break;
        }
        return true;
    }
}
