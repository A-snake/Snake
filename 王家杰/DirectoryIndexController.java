package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DirectoryIndexController {
    @PostMapping("/index")
    public String indexFiles(@RequestPart("files") MultipartFile[] files) {
        // 获取前端传递的文件数组，并打印路径
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                System.out.println("选择的目录路径：" + file.getOriginalFilename());
            }
        }

        return "success";
    }
}
