package com.zbao.news.config;

import com.squareup.otto.Bus;

/**
 * OTTO 事件总线单例模式
 * Created by zhangYB on 2016/4/26.
 */
public class OttoService {


    private OttoService() {
    }

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

}
