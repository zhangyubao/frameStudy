package com.zbao.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.zbao.news.base.BaseActivity;
import com.zbao.news.custonView.LoadProgressView;
import com.zbao.news.utils.SharedPreferenceUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangYB on 2016/4/29.
 */
public class FlashActivity extends BaseActivity {

    private int SPLASH_TIME = 100;

    private Timer mTimer = new Timer();
    private TimerTask mTimerTask;

    @Bind(R.id.lpv_enter)
    LoadProgressView mProgressView;

    static {
        System.loadLibrary("news");
    }


    /**
     * java JNI 使用
     * 1、安装NDK  AS直接安装
     * <p/>
     * 2、在src/main 目录下创建jni文件夹
     * <p/>
     * 3、定义native方法
     * static{    //加载so库
     *      System.loadLibrary("news");
     * }
     * 4、通过javah生成 .h头文件 (注意①命令行进入到G:\AsProject\News\app\src\main>文件夹下②依赖文件E:\assdk\extras\android\m2repository\com\android\support\appcompat-v7\23.3.0\appcompat-v7-23.3.0-sources.jar )
     * G:\AsProject\News\app\src\main>javah -d jni -classpath E:\assdk\platforms\android-23\android.jar;E:\assdk\extras\android\m2repository\com\android\support\appcompat-v7\23.3.0\appcompat-v7-23.3.0-sources.jar;G:\AsProject\News\app\build\intermediates\clas
     * ses\debug com.zbao.news.FlashActivity
     * <p/>
     * 5、在jni文件夹下创建相应的.c文件
     *
     * @return
     */
    private native String getUrl();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mProgressView.setText("跳过");
            Logger.e(getUrl());
            mProgressView.setProgress(SPLASH_TIME);
            if (SPLASH_TIME == 0) {
                if (SharedPreferenceUtils.getInstallState(mContext)) {
                    enterMain();
                } else {
                    enterGuide();
                }
            }
            SPLASH_TIME = SPLASH_TIME - 1;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        mTimer.schedule(mTimerTask, 0, 50);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_flash;
    }

    @OnClick(R.id.lpv_enter)
    public void enterMain() {
        if (mTimer != null)
            mTimer.cancel();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 进入向导界面
     */
    private void enterGuide() {
        if (mTimer != null)
            mTimer.cancel();
        startActivity(new Intent(this, GuideaActivity.class));
        finish();
    }
}
