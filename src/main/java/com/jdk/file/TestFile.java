package com.jdk.file;

import org.junit.jupiter.api.Test;

import java.io.*;
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
    public void test_demo_2023_06_04_16_36_21() {
//        File[] files = new File("/Users/zck/Develop/projectFileTemp/c790fcc3d866e2bd43df4f8ece9af5a5").listFiles();
//        calculateHash(List.of(files))
//                .thenAccept(md5 -> System.out.println("文件的MD5值: " + md5))
//                .exceptionally(e -> {
//                    System.out.println("无法计算文件的MD5值: " + e.getMessage());
//                    return null;
//                });
        File file = new File("/Users/zck/Develop/projectFile/2023-06-04/2023-06-04-17/2e2c2329c7cdfa878ae0f4c175bf38ad.mp4");
        List<File> fileChunkList = splitFile(file);

        calculateHash(fileChunkList)
                .thenAccept(md5 -> System.out.println("文件的MD5值: " + md5))
                .exceptionally(e -> {
                    System.out.println("无法计算文件的MD5值: " + e.getMessage());
                    return null;
                });
    }

    private static List<File> splitFile(File file) {
        long fileSize = file.length();
        int offset = 2 * 1024 * 1024; // 偏移量

        List<File> chunks = new ArrayList<>();
        long cur = 0;
        while (cur < fileSize) {
            if (cur + offset >= fileSize) {
                chunks.add(file);
            } else {
                long mid = cur + offset / 2;
                long end = cur + offset;
                chunks.add(sliceFile(file, cur, cur + 2));
                chunks.add(sliceFile(file, mid, mid + 2));
                chunks.add(sliceFile(file, end - 2, end));
            }
            cur += offset;
        }
        return chunks;
    }

    private static File sliceFile(File file, long start, long end) {
        try {
            byte[] buffer = new byte[(int) (end - start)];
            try (FileInputStream inputStream = new FileInputStream(file)) {
                inputStream.skip(start);
                inputStream.readNBytes(buffer, 0, buffer.length);
            }
            File tempFile = File.createTempFile(null, null);
            Files.write(tempFile.toPath(), buffer);
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static CompletableFuture<String> calculateHash(List<File> fileChunkList) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                MessageDigest md5Digest = MessageDigest.getInstance("MD5");
                for (File chunk : fileChunkList) {
                    byte[] chunkBytes = Files.readAllBytes(chunk.toPath());
                    md5Digest.update(chunkBytes);
                }
                byte[] hashBytes = md5Digest.digest();
                StringBuilder md5Builder = new StringBuilder();
                for (byte b : hashBytes) {
                    md5Builder.append(String.format("%02x", b));
                }
                return md5Builder.toString();
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
                throw new RuntimeException("无法计算MD5值");
            }
        });
    }

    public static void createDirectories(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }
    }

    @Test
    public void test_demo_2023_06_04_17_00_36() {
        String filePath = "/Users/zck/Develop/projectFileTemp/1/1/1/1/1/2.txt";
        createDirectories(filePath);
        System.out.println("文件夹已创建");
    }
}
