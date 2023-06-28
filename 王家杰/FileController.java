package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FileController {
    @PostMapping("/upload")
    @ResponseBody
    public List<String> upload(MultipartHttpServletRequest request) throws IOException {
        List<String> fileList = new ArrayList<>();

        // 获取上传的文件夹
        CommonsMultipartFile folder = (CommonsMultipartFile) request.getFile("folder");
        if (folder != null && folder.getSize() > 0) {
            File uploadDir = new File("upload"); // 文件上传目录
            uploadDir.mkdirs();

            File folderDir = new File(uploadDir, "folder");
            folderDir.mkdir();

            // 保存文件夹
            String folderPath = folderDir.getAbsolutePath();
            File savedFolder = new File(folderPath);
            folder.transferTo(savedFolder);

            // 获取子文件列表
            if (savedFolder.isDirectory()) {
                File[] files = savedFolder.listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                }
            }

            // 删除临时文件夹
            FileSystemUtils.deleteRecursively(folderDir);
        }

        return fileList;
    }
}
