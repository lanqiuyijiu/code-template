package com.jdk.net.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author ck created in 2021/9/13 14:00
 */
public class HttpUtil {
    public static String doGet(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                // 忽略掉https 证书的校验
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                // 设置超时时间
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();


        Request request = new Request.Builder().get().url(url)
                .header("User-Agent", "Mozilla/5.0" +
                                      " (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                .build();
        String httpCode;
        try (Response response = client.newCall(request).execute()) {
            httpCode = String.valueOf(response.code());
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return httpCode;
    }


    @Test
    public void testDoGet() {
//        String s = doGet("https://115.239.190.131").replaceAll("\n", "");
//        System.out.println(doGet("https://dd.yytlms.com/agapp/jobTask/allJobtask"));
        System.out.println(doGet("https://ke.qq.com/webcourse/398381/100475149#taid=4067273900168237&vid=5285890806011077757"));

//        System.out.println(5%4);
    }
}
