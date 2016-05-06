package com.zbao.news.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangYB on 2016/4/29.
 */
public class NewsApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
