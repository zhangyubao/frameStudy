package com.zbao.news.internet;

import com.zbao.news.config.AppConstants;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 获取服务器数据包装类
 * Created by Administrator on 2016/4/18.
 */
public class RetrofitWapper {

    private Retrofit mRetrofit;
    private static RetrofitWapper mRetrofitWapper;

    private RetrofitWapper() {
        mRetrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(AppConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitWapper getInstance() {
        if (mRetrofitWapper == null) {
            synchronized (RetrofitWapper.class) {
                if (mRetrofitWapper == null) {
                    mRetrofitWapper = new RetrofitWapper();
                }
            }
        }
        return mRetrofitWapper;
    }

    /**
     * 生成service对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
