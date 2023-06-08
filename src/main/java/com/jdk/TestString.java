package com.jdk;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zck created in 2023/3/16 18:03
 */
public class TestString {
    @Test
    public void test_2023_03_16_18_03_14() {
        String str = "123456789";
        System.out.println(str.substring(0, 4));
        System.out.println(str);
    }

    @Test
    public void test_2023_03_17_13_58_56() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(null);
        System.out.println();
    }

    @Test
    public void test_demo_2023_04_07_18_43_27() {
        System.out.println(UUID.randomUUID().toString());
    }

    public static String calculateMD5(List<String> stringList) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        for (String str : stringList) {
            md.update(str.getBytes());
        }

        byte[] digest = md.digest();

        // 将字节数组转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    @Test
    public void test_demo_2023_06_08_16_18_42() {
        List<String> stringList = List.of("Hello", "World", "OpenAI");

        try {
            String md5 = calculateMD5(stringList);
            System.out.println("MD5: " + md5);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
