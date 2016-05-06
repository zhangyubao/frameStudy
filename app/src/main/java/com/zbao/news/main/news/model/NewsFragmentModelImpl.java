package com.zbao.news.main.news.model;

import com.zbao.news.entity.JokeInf;
import com.zbao.news.internet.InternetService;
import com.zbao.news.internet.RetrofitWapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangYB on 2016/5/3.
 */
public class NewsFragmentModelImpl implements NewsFragmentModel {

    @Override
    public void getNewsList(final OnNewsLoadFinishedListener listener) {
        final Call<JokeInf> list = RetrofitWapper.getInstance().create(InternetService.class).getNewsList("desc", 1, 20, "1418816972", "eb46c85bea73462583e38b84c3a25c4b");
        list.enqueue(new Callback<JokeInf>() {
            @Override
            public void onResponse(Call<JokeInf> call, Response<JokeInf> response) {
                listener.onSucess(response.body());
            }

            @Override
            public void onFailure(Call<JokeInf> call, Throwable t) {
                listener.onFailure("get news list failure", t);

            }
        });
    }

    /**
     * 新闻数据加载完成回调接口
     */
    public interface OnNewsLoadFinishedListener {
        //数据记载成功
        void onSucess(JokeInf jokeInf);

        //数据加载失败
        void onFailure(String tag, Throwable throwable);
    }
}
