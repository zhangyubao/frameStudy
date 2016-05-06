package com.zbao.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

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

    private int SPLASH_TIME = 3;

    private Timer mTimer = new Timer();
    private TimerTask mTimerTask;

    @Bind(R.id.lpv_enter)
    LoadProgressView mProgressView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
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
        mTimer.schedule(mTimerTask, 0, 1000);
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
