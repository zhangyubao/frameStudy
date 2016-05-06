package com.zbao.news.main.news.view;

import com.zbao.news.entity.JokeInf;

import java.util.List;

/**
 * Created by zhangYB on 2016/5/3.
 */
public interface NewFragmentView {
    /**
     * 适配RecyclarView数据
     */
    void setDataForList(List<JokeInf.ResultBean.DataBean> news);

    /**
     * 显示加载进度条
     */
    void showProgress();

    /**
     * 隐藏加载进度条
     */
    void hideProgress();

}
