package com.example.entity;

import java.io.File;

public class LocalDiskReader {
    public File[] getDisk() {
        // 获取本地磁盘的根目录列表
        File[] roots = File.listRoots();

        // 遍历磁盘根目录列表
        for (File root : roots) {
            System.out.println("磁盘名称: " + root.getAbsolutePath());
        }
        return roots;
    }
}
