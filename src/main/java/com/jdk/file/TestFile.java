package com.jdk.file;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zck created in 2023/6/2 19:35
 */
public class TestFile {
    @Test
    public void test_demo_2023_06_02_19_35_28() {
        File folder = new File("/Users/zck/Develop/project-trace/test/projectFile/26d777328942482fa3440f84b7dad9c0");
        File[] files = folder.listFiles();
        HashMap<Integer, File> map = new HashMap<>();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            File file = files[i];
            if (file.isFile() && !file.isHidden()) {
                map.put(Integer.valueOf(file.getName()), file);
            }
        }
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream("/Users/zck/Develop/project-trace/test/projectFile/26d777328942482fa3440f84b7dad9c1/rest"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 1; i <= map.size(); i++) {
            File file = map.get(i);
            System.out.println(file.getName());
            try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
