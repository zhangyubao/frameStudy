package com.zbao.news.network;

import com.zbao.news.entity.JokeInf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 网络接口类,定义所有的数据获取接口
 * Created by Administrator on 2016/4/18.
 */
public interface InternetService {
    /**
     * 获取笑话列表
     *
     * @param sort     排序  desc倒序
     * @param page     页码
     * @param pagesize 每页的条数
     * @param time     请求时间
     * @param key      应用的key
     * @return
     */
    @GET("/joke/content/list.from")
    Call<JokeInf> getNewsList(@Query("sort") String sort, @Query("page") int page, @Query("pagesize") int pagesize, @Query("time") String time, @Query("key") String key);

    /**
     * Rxjava方式获取网络数据
     *
     * @param sort
     * @param page
     * @param pagesize
     * @param time
     * @param key
     * @return
     */
    @GET("/joke/content/list.from")
    Observable<JokeInf> getJokeListByRxjava(@Query("sort") String sort, @Query("page") int page, @Query("pagesize") int pagesize, @Query("time") String time, @Query("key") String key);

}
