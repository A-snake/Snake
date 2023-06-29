package com.example.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.service.FileSearch.getFileExtension;

public class DiskExplorer {

    public List<String>  getDirectory(File directory) {
        // 获取目录下的所有文件和子目录
        File[] files = directory.listFiles();
        List<String> result =new ArrayList<>();

        // 遍历文件和子目录
        for (File file : files) {
            String name = file.getName();
            if (file.isDirectory()) {
                System.out.println("目录: " + name);
                //exploreDirectory(file);  // 递归遍历子目录
                result.add(String.valueOf(file));
            } else {
                String extension = getFileExtension(name);
                if (extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("pdf")) {
                    System.out.println("文件: " + name);
                    result.add(String.valueOf(file));
                }
            }
        }
        return result;
    }

}