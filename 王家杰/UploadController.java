package com.example.controller;

import com.example.entity.DirectoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    //@RequestParam("selectedDirectory") String selectedDirectory
    public String uploadFile(@RequestPart("file") MultipartFile file){
    //@RequestBody String folderPath
        if (!file.isEmpty()) {
            System.out.println("上传的文件名：" + file.getOriginalFilename());
            System.out.println("文件大小：" + file.getSize() + " bytes");
            // 其他处理逻辑...
        } else {
            System.out.println("上传的文件为空.");
        }


        //System.out.println("test");
        return "success";
    }
}
