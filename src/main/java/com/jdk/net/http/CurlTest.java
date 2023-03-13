package com.jdk.net.http;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zck created in 2022/12/1 16:25:22
 */
public class CurlTest {

    @Test
    public void execCurl() {
        ProcessBuilder process = new ProcessBuilder(
                "curl", "-X", "GET",
//                "-sIL -w",
                "https://dd.yytlms.com/agapp/jobTask/allJobtask",
                "-H", "accept: */*", "-H", "Content-Type: application/json;charset=UTF-8",
                "{ \\\"bodyName\\\": \\\"bodyValue\\\"}");
        Process p;

        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            System.out.println(builder.toString());

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {
        String url = "https://dd.yytlms.com/agapp/jobTask/allJobtask";
        ProcessBuilder pb = new ProcessBuilder("curl", "-k", "-I", "-s", "-o", "/dev/null", "-w", "%{http_code}", url);

        Process p = pb.start();
        String s = new BufferedReader(new InputStreamReader(p.getInputStream())).readLine();
        System.out.println(s);
    }

}
