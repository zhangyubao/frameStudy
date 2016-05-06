package com.zbao.news.main.news.model;

/**
 * Created by zhangYB on 2016/5/3.
 */
public interface NewsFragmentModel {
    /**
     * 获取新闻列表
     *
     * @param listener
     */
    public void getNewsList(NewsFragmentModelImpl.OnNewsLoadFinishedListener listener);
}
