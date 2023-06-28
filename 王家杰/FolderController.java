package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FolderController {
    //@PostMapping("/page/index.html")
    public void handleFileUpload(@RequestBody String folderPath) {
        System.out.println("接收到的文件夹路径：" + folderPath);
    }
}
