package com.jdk.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileMD5Calculator {
    public static void main(String[] args) {
        File directory = new File("/Users/zck/Develop/project-trace/test/projectFileTemp/056c410c619891986e168838a7b85d51");
        String md5 = calculateDirectoryMD5(directory);
        System.out.println("Total MD5: " + md5);
    }

    public static String calculateDirectoryMD5(File directory) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        // 获取目录下的所有文件
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }

        // 逐个计算文件的MD5并更新到总的MD5值
        for (File file : files) {
            if (file.isFile()) {
                String fileMD5 = calculateFileMD5(file);
                md.update(fileMD5.getBytes());
            }
        }

        // 计算总的MD5值
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

    public static String calculateFileMD5(File file) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        try (FileInputStream fis = new FileInputStream(file);
             DigestInputStream dis = new DigestInputStream(fis, md)) {

            // 读取文件内容
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) {
                // 仅读取内容以更新MD5值
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // 获取计算得到的文件MD5值
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
