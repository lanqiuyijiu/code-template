package com.jdk.net.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.time.Duration;

/**
 * @author ck created in 2021/9/13 14:00
 */
@Slf4j
public class HttpUtil {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .callTimeout(Duration.ofSeconds(30)).build();// 设置总超时时间30秒

    public static String doGet(String url) {
        Request request = new Request.Builder().get().url(url).build();
        String result;
        try (Response response = client.newCall(request).execute()) {
            result = String.valueOf(response.code());
        } catch (Exception e) {
            log.error("okhttp 请求 {} 异常  url {} ", url, e.getMessage());
            if (CurlTest.doGet(url).equals("200")) {
                result = "200";
            } else {
                result = e.getMessage();
            }
        }
        return result;
    }

    public static String doPost(String url, String jsonContent) {
        RequestBody body = RequestBody.create(jsonContent, MediaType.get("application/json"));
        Request request = new Request.Builder().url(url).post(body).build();
        String result = "";
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (IOException e) {
            result = e.getMessage().replaceAll("\n", "");
        }
        return result;
    }
}