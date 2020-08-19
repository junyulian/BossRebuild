package com.example.bossrebuild.activity;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageView;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityVideoBinding;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/7
 */
public class VideoActivity extends BaseActivity<ActivityVideoBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

    }



    OrientationUtils orientationUtils;

    private void init() {


        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        binding.videoPlayer.setUp(source1, true, "牛逼视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.test_gold);
        binding.videoPlayer.setThumbImageView(imageView);
        //增加title
        binding.videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        binding.videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, binding.videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        binding.videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        binding.videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        binding.videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.videoPlayer.startPlayLogic();

    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            binding.videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        binding.videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
