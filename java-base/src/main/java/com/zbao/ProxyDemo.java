package com.zbao;

import retrofit2.http.GET;


/**
 * java 动态代理代码示例
 */
public interface ProxyDemo {

    /**
     * 加法操作
     *
     * @param a
     * @param b
     */
    @GET("default")
    public int add(int a, int b);

   /* CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    Certificate certificate = certificateFactory.generateCertificate(inputStream);
    KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
    keyStore.load(null,null);
    keyStore.setCertificateEntry("trust",certificate);

    TrustManagerFactory trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(keyStore);
    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(null,trustManagerFactory.getTrustManagers(),null);
    mOkHttpClient=new

    OkHttpClient();

    mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
    mOkHttpClient.setHostnameVerifier(new

    HostnameVerifier() {
        @Override
        public boolean verify (String hostname, SSLSession session){
            if (HOST_NAME.equals(hostname))
                return true;
            return false;
        }
    }

    );*/
}
