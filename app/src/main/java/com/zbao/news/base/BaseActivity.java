package com.zbao.news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/4/29.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        Logger.init().hideThreadInfo().setLogLevel(LogLevel.FULL);
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    public abstract int getLayout();
}
