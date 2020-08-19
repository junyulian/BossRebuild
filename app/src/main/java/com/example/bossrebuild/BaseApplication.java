package com.example.bossrebuild;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/3
 */
public class BaseApplication extends Application {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mainThreadId = android.os.Process.myTid();// 获取当前主线程id
        handler = new Handler();
    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }
}
