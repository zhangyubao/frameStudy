package com.zbao.news.greendao.utils;

import android.content.Context;

import com.zbao.news.greendao.dao.DaoMaster;
import com.zbao.news.greendao.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 核心辅助类。用于获取DaoMaster和DaoSession
 * <p/>
 * Created by zhangYB on 2016/5/5.
 */
public class DBCore {
    //默认数据库名称
    private static final String DEFAULT_DB_NAME = "default.db";
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private static Context mContext;
    private static String DB_NAME;

    /**
     * greendao 初始化
     *
     * @param mContext
     */
    public static void initialize(Context mContext) {
        initialize(mContext, DEFAULT_DB_NAME);
    }

    /**
     * greendao 初始化
     *
     * @param context
     * @param dbName
     */
    public static void initialize(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("当前上下文环境不能为空");
        }
        mContext =context.getApplicationContext();
        DB_NAME = dbName;
    }

    /**
     * 获取数据库管理者
     *
     * @return
     */
    public static DaoMaster getDaoMaster() {
        try {
            if (mDaoMaster == null) {
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
                mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mDaoMaster;
    }

    /**
     * 获取数据库会话
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    public static void enableQueryBuilderLog() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
