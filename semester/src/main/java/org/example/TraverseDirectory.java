package org.example;

import java.io.File;

public class TraverseDirectory {
    public static void main(String[] args) {
        // 定义目录路径
        String path = "D:\\OneDrive\\桌面\\大学";
        // 创建File对象
        File directory = new File(path);
        // 调用遍历目录的方法
        traverseDirectory(directory);
    }

    // 定义遍历目录的方法
    public static void traverseDirectory(File directory) {
        // 获取目录下的所有文件和子目录
        File[] files = directory.listFiles();

        // 遍历文件和子目录
        for (File file : files) {
            // 如果是文件，则输出文件路径
            if (file.isFile() && (file.getName().endsWith(".pdf") || file.getName().endsWith(".doc"))) {

//                if (file.isFile() ) {
//                    System.out.println(file.getAbsolutePath());
//                }
//
                    System.out.println(file.getAbsolutePath());
            }
            // 如果是目录，则递归调用遍历目录的方法
            else if (file.isDirectory()) {
                traverseDirectory(file);
            }
        }
    }
}
