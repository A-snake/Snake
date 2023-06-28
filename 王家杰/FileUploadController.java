package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 处理文件上传逻辑
        if (!file.isEmpty()) {
            try {
                // 获取文件信息
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();

                // 进一步处理文件，例如保存到服务器或执行其他操作

                System.out.println(size);
                // 返回上传成功页面或其他结果页面
                return "success";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}