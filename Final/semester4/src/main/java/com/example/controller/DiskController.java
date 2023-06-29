package com.example.controller;

import com.example.entity.DiskExplorer;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class DiskController {

        @GetMapping("/getDiskList")
        public List<String> getDiskList() {
            // 模拟返回磁盘名称列表的数据
            List<String> diskList = new ArrayList<>();
            File[] roots = File.listRoots();

            // 遍历磁盘根目录列表
            for (File root : roots) {
                System.out.println("磁盘名称: " + root.getAbsolutePath());
                diskList.add(String.valueOf(root));
            }
            return diskList;
        }
        @PostMapping("/getDiskInfoD")
        public List<String> getDataD(){
            DiskExplorer de = new DiskExplorer();
            List<String> directory = de.getDirectory(new File("D:\\"));
            return directory;
        }
    @PostMapping("/getDiskInfoC")
        public List<String> getDataC(){
        DiskExplorer de = new DiskExplorer();
        List<String> directory = de.getDirectory(new File("C:\\"));
        return directory;
    }

}
