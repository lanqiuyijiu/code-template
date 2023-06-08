package com.jdk.file;

import com.mysql.cj.log.Log;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zck created in 2022/12/1 16:25:22
 */
@Log4j2
public class FileUtils {
    public static void createFile(String path) {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                log.info("文件夹创建失败");
            }
        }
    }

    public static void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        // 检查文件夹是否存在
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 删除文件
                    if (!file.delete()) {
                        log.info("删除文件夹失败");
                    }
                }
            }
        }
        if (!folder.delete()) {
            log.info("删除文件夹失败");
        }
    }
}
