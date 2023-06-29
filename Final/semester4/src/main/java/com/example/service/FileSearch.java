package com.example.service;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class FileSearch {
    public List<String> getFilePath(){
        String directoryPath = "D:\\upload-directory";
        List<String> fileExtensions =  Arrays.asList("doc", "pdf");
        List<String> filePaths = searchFiles(directoryPath, fileExtensions);

        return filePaths;
    }
    public static void main(String[] args) {
        String directoryPath = "D:\\upload-directory";
        List<String> fileExtensions =  Arrays.asList("doc", "pdf");

        List<String> filePaths = searchFiles(directoryPath, fileExtensions);
        for (String filePath : filePaths) {
            System.out.println(filePath);
        }
    }

    public static List<String> searchFiles(String directoryPath, List<String> fileExtensions) {
        List<String> filePaths = new ArrayList<>();

        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        String extension = getFileExtension(fileName);
                        if (fileExtensions.contains(extension)) {
                            filePaths.add(file.getAbsolutePath());
                        }
                    } else if (file.isDirectory()) {
                        filePaths.addAll(searchFiles(file.getAbsolutePath(), fileExtensions));
                    }
                }
            }
        }

        return filePaths;
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}
