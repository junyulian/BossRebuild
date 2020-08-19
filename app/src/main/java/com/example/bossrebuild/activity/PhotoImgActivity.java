package com.example.bossrebuild.activity;

import android.graphics.Bitmap;
import android.view.View;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityPhotoImgBindingImpl;


/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/7
 */
public class PhotoImgActivity extends BaseActivity<ActivityPhotoImgBindingImpl> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_img;
    }


    @Override
    protected void initView() {

        binding.ivPhoto.setResourceBitmap(this,R.mipmap.test);


        binding.tvAction.setOnClickListener(v -> {

            try {

                binding.ivResult.setBackgroundResource(0);
                binding.ivResult.setImageDrawable(null);
                binding.ivResult.setImageBitmap(null);

                binding.ivResult.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        int width = binding.flMask.getWidth();
                        int height = (int)binding.flMask.getRealHeight();

                        Bitmap bitmap = null;
                        try {
                            bitmap = getBitmap(width,height,binding.flMask);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        binding.ivResult.setImageBitmap(bitmap);

                    }
                },500);



            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    private Bitmap getBitmap(int outWidth, int outHeight,View frameView) throws Exception {

        View screenView = getWindow().getDecorView();
        screenView.setDrawingCacheEnabled(true);
        screenView.buildDrawingCache();

        //获取屏幕整张图片
        Bitmap bitmap = screenView.getDrawingCache();

        if (bitmap != null) {

            //获取需要截图部分的在屏幕上的坐标(view的左上角坐标）
            int[] viewLocationArray = new int[2];
            frameView.getLocationOnScreen(viewLocationArray);

            //从屏幕整张图片中截取指定区域
            bitmap = Bitmap.createBitmap(bitmap, viewLocationArray[0], viewLocationArray[1], outWidth, outHeight);

        }
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }



    @Override
    protected void initData() {

    }
}
