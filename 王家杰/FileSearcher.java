package org.example;

import java.io.File;

public class FileSearcher {
    public static void main(String[] args) {
        String directoryPath = "D:\\OneDrive\\桌面\\大学\\操作系统复习"; // 修改为你要查询的目录路径
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("指定的路径不是一个目录！");
            return;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                    System.out.println(file.getAbsolutePath());
            }
        }
    }
}
