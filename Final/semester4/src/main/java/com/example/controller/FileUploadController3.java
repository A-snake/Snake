package com.example.controller;

import com.example.service.DocKeywordSearch;
import com.example.service.FileSearch;
import com.example.service.PdfKeywordSearch;
import com.example.uitl.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.service.FileSearch.getFileExtension;

@RestController
public class FileUploadController3 {

    private StringBuilder outputContent = new StringBuilder();

    public static final String UPLOAD_DIRECTORY = "D:\\upload-directory"; // 上传文件保存的目录
    @PostMapping("/upload")
    public R uploadFolder(@RequestParam("folder") MultipartFile[] files,
                          @RequestParam("keyword") String keyword) {
        File directory = new File(UPLOAD_DIRECTORY);

        // 创建上传文件保存的目录（如果不存在）
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 遍历上传的文件列表，保存每个文件到指定目录
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                //   System.out.println(fileName);
                //   System.out.println(keyword);
                try {
                    // 使用transferTo方法将文件保存到指定目录
                    file.transferTo(new File(directory, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    return new R("上传失败");
                }
            }
        }

        DocKeywordSearch docKeywordSearch = new DocKeywordSearch();
        PdfKeywordSearch pdfKeywordSearch = new PdfKeywordSearch();

        FileSearch fs = new FileSearch();
        List<String> filePath = fs.getFilePath();
        for (String s : filePath) {
            File file = new File(s);
            String extension = getFileExtension(file.getName());

            if (extension.equalsIgnoreCase("doc")) {
                System.out.println(s);

                outputContent.append("\n");
                outputContent.append(s).append("\n");
                outputContent.append("---------------------------------------").append("\n");
                System.out.println("------------------------------------------------------------------------");
               // docKeywordSearch.doSearch(s,keyword);
                List<String> strings = docKeywordSearch.doSearch(s, keyword);
                for (String string : strings) {
                    outputContent.append(string).append("\n");
                }
                outputContent.append("\n");
            } else if (extension.equalsIgnoreCase("pdf")) {

                outputContent.append("\n");
                outputContent.append(s).append("\n");
                outputContent.append("---------------------------------------").append("\n");

                System.out.println(s);
                System.out.println("------------------------------------------------------------------------");
                //pdfKeywordSearch.pdfSearch(s,keyword);
                List<String> strings = pdfKeywordSearch.pdfSearch(s, keyword);
                for (String string : strings) {
                    outputContent.append(string).append("\n");
                }
                outputContent.append("\n");

            } else {
                System.out.println("Unsupported file type: " + extension);
            }
            System.out.println();
        }

        return new R(String.valueOf(outputContent));
    }

}
