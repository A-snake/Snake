package com.example.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfKeywordSearch {
    public List<String> pdfSearch(String filePath,String keyword){
        List<String> result = new ArrayList<>();

        try {
            PDDocument document = PDDocument.load(new File(filePath));
            PDFTextStripper stripper = new PDFTextStripper();

            int pageNumber = 1;

            // 设置提取范围：从第一页到最后一页
            stripper.setStartPage(1);
            stripper.setEndPage(document.getNumberOfPages());

            // 获取整个文档的文本内容
            String content = stripper.getText(document);

            // 分割成每行的字符串数组
            String[] lines = content.split("\\r?\\n");

            // 遍历每一行进行关键字检索并输出行号和内容
            for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
                String line = lines[lineNumber];
                if (line.contains(keyword)) {
                    System.out.println("Line " + (lineNumber + 1) + ": " + line);
                    line  = "Line " + (lineNumber + 1) + ": " + line;

                    result.add(line);
                }
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        String filePath = "D:\\upload-directory\\操作系统-样卷B.pdf";
        String keyword = "操作";

        try {
            PDDocument document = PDDocument.load(new File(filePath));
            PDFTextStripper stripper = new PDFTextStripper();

            int pageNumber = 1;

            // 设置提取范围：从第一页到最后一页
            stripper.setStartPage(1);
            stripper.setEndPage(document.getNumberOfPages());

            // 获取整个文档的文本内容
            String content = stripper.getText(document);

            // 分割成每行的字符串数组
            String[] lines = content.split("\\r?\\n");

            // 遍历每一行进行关键字检索并输出行号和内容
            for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
                String line = lines[lineNumber];
                if (line.contains(keyword)) {
                    System.out.println("Line " + (lineNumber + 1) + ": " + line);
                }
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
