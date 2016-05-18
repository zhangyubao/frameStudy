package com.zbao.news.network;

import android.content.Context;

import com.zbao.news.R;
import com.zbao.news.config.AppConstants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by zhangYB on 2016/5/12.
 */
public class RetrofitHttpsWapper {

    private static RetrofitHttpsWapper sHttpsWapper;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient = creatOkHttpClient();
    private static Context mContext;
    private static final String RESPONSE_CACHE = "/data/data/com.zbao.news";
    public static final int CACHE_SIZE = 500;

    private RetrofitHttpsWapper() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(AppConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 创建可加载证书的Https证书客户端
     */
    private OkHttpClient creatOkHttpClient() {
        OkHttpClient okHttpClient = null;
        InputStream inputStream = null;
        try {
            /*Note：
             *此处要替换成自己的证书
             */
            inputStream = mContext.getResources().openRawResource(R.raw.certificate);
            //读取证书
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(inputStream);
            //创建一个证书库，并将证书导入证书库
            KeyStore keyStore = KeyStore.getInstance("PBCS12", "BC");
            keyStore.load(null, null);
            keyStore.setCertificateEntry("trust", certificate);
            //把自己的证书库作为信任证书库
            TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            managerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, managerFactory.getTrustManagers(), null);
            //创建会话客户端
            okHttpClient = new OkHttpClient();
            OkHttpClient.Builder builder = okHttpClient.newBuilder();
            //设置缓存文件
            File cacheDir = new File(mContext.getCacheDir(), RESPONSE_CACHE);
            builder.cache(new Cache(cacheDir, CACHE_SIZE));
            // 设置信任服务器主机
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (AppConstants.SERVER_URL.equals(hostname)) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (okHttpClient == null) {
            throw new RuntimeException("generator okhttpclient defeated make sure you have no failure");
        }
        return okHttpClient;

    }

    /**
     * 单利模式
     *
     * @return
     */
    public static RetrofitHttpsWapper build(Context context) {
        mContext = context;
        if (sHttpsWapper == null) {
            synchronized (RetrofitHttpsWapper.class) {
                if (sHttpsWapper == null) {
                    sHttpsWapper = new RetrofitHttpsWapper();
                }
            }
        }
        return sHttpsWapper;
    }

    public <T> T crate(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
