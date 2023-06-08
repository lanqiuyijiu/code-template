package com.jdk.file;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @author zck created in 2023/6/2 19:35
 */
public class TestFile {
    @Test
    public void test_demo_2023_06_02_19_35_28() throws IOException {
        File folder = new File("/Users/zck/Develop/temp/c790fcc3d866e2bd43df4f8ece9af5a5");
        File[] files = folder.listFiles();
        HashMap<Integer, File> map = new HashMap<>();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            File file = files[i];
            if (file.isFile() && !file.isHidden()) {
                map.put(Integer.valueOf(file.getName()), file);
            }
        }

        FileOutputStream fos = new FileOutputStream("/Users/zck/Develop/temp/res1t.mp4");
//        OutputStream output = null;
//        try {
//            output = new BufferedOutputStream(new FileOutputStream(""));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        for (int i = 0; i < map.size(); i++) {
            File file = map.get(i);
            System.out.println(file.getName());

            FileInputStream fis = new FileInputStream(file);
            // 创建一个缓冲区
            byte[] buffer = new byte[1024];
            int bytesRead;

            // 逐个读取分片文件，并写入合并文件
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            // 关闭当前分片文件的输入流
            fis.close();

        }
        fos.close();
        System.out.println("File merged successfully.");
    }


    @Test
    public void test_demo_2023_06_08_17_01_44() {
        String filePath = "/Users/zck/Desktop/1441a7909c087dbbe7ce59881b9df8b9.xlsx";
        String extension = "";
        int indexOfLastExtension = filePath.lastIndexOf(".");
        // 检查windows, linux 最后一个文件分隔符
        int lastSeparatorPosWindows = filePath.lastIndexOf("\\");
        int lastSeparatorPosUnix = filePath.lastIndexOf("/");
        // 最后文件分隔符位置， 取最大值
        int indexOfLastSeparator = Math.max(lastSeparatorPosWindows, lastSeparatorPosUnix);
        // 确保.分隔符在文件分隔符之后.
        if (indexOfLastExtension > indexOfLastSeparator) {
            extension = filePath.substring(indexOfLastExtension);
        }
        System.out.println(extension);
    }

    @Test
    public void test_demo_2023_06_08_22_09_12() {
        System.out.println(md5HashCode("/Users/zck/Movies/2021年图灵学院 - JAVA高级架构师 (第4期)/视频/15-6 ElasticSearch安装使用教程（2）-.mp4"));
    }

    @Test
    public void test_demo_2023_06_08_23_23_45() throws NoSuchAlgorithmException {
        File[] files = new File("/Users/zck/Develop/project-trace/test/projectFileTemp/1d15f94dfcf17580a36e318cf1d72ea2").listFiles();
        // 将文件列表有序处理
        HashMap<Integer, File> map = new HashMap<>();
        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                map.put(Integer.valueOf(file.getName()), file);
            }
        }
        String path = "/Users/zck/Develop/project-trace/111.mp4";
        FileUtils.createFile(path);
        FileOutputStream fos;
        MessageDigest md = MessageDigest.getInstance("MD5");
        try {
            fos = new FileOutputStream(path);
            for (int i = 0; i < map.size(); i++) {
                File file = map.get(i);
                FileInputStream fis = new FileInputStream(file);
                // 创建一个缓冲区
                byte[] buffer = new byte[1024];
                int bytesRead;
                // 逐个读取分片文件，并写入合并文件
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                    md.update(buffer, 0, bytesRead);
                }
                // 关闭当前分片文件的输入流
                fis.close();
                //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            }
            fos.close();
            byte[] byteArray = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5HashCode(String filePath) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream("path");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            fis.close();

            byte[] byteArray = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
}
