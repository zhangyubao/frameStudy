package com.zbao.news.app;

import android.app.Application;
import android.content.Context;

import com.zbao.news.greendao.utils.DBCore;

/**
 * Created by zhangYB on 2016/4/29.
 */
public class NewsApplication extends Application {


    private static Context mContext;
    //数据库名称
    private static final String DB_NAME = "mobile.db";//可以是文件(mobile.db)也可以是路径加文件名（data/data/com.zbao.news/mobile.db）

    @Override
    public void onCreate() {
        super.onCreate();
        DBCore.initialize(this, DB_NAME);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
