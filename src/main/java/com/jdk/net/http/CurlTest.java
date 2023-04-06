package com.jdk.net.http;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zck created in 2023/3/3 11:00
 */
@Slf4j
public class CurlTest {
    private static ProcessBuilder processBuilder;

    private static ProcessBuilder instance(String url) {
        if (processBuilder == null) {
            processBuilder = new ProcessBuilder("curl",
                    "--max-time", "5", "-o", "/dev/null", "-s", "-w", "%{http_code}", url);
        }
        return processBuilder;
    }

    public static String doGet(String url) {
        Process process;
        String result;
        try {
            process = instance(url).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            result = reader.readLine();
            log.info("curl request");
        } catch (IOException e) {
            log.error("curl 请求 {} 异常  {}", url, e.getMessage());
            throw new RuntimeException(e);
        }
        log.info(result);
        return result;
    }
}
