package com.zbao.news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

/**
 * Created by zhangYB on 2016/4/29.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
