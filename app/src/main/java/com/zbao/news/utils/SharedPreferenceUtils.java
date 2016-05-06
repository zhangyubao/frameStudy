package com.zbao.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangYB on 2016/5/4.
 */
public class SharedPreferenceUtils {

    /**
     * 保存安装状态
     *
     * @param context
     * @param state
     */
    public static void saveInstallState(Context context, boolean state) {
        SharedPreferences isInstall = context.getSharedPreferences("install", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = isInstall.edit();
        editor.putBoolean("state", state);
        editor.commit();
    }

    /**
     * 获取安装状态(是否是第一次安装)
     *
     * @param context
     * @return
     */
    public static boolean getInstallState(Context context) {
        SharedPreferences isInstall = context.getSharedPreferences("install", Context.MODE_PRIVATE);
        return isInstall.getBoolean("state", false);
    }


}
