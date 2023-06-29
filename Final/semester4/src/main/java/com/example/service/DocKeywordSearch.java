package com.example.service;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocKeywordSearch {
    public List<String> doSearch (String filePath,String keyword){
        List<String> result = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HWPFDocument document = new HWPFDocument(fs);

            Range range = document.getRange(); // 获取整个文档范围

            List<String> lines = new ArrayList<>();
            for (int i = 0; i < range.numParagraphs(); i++) {
                Paragraph paragraph = range.getParagraph(i); // 获取段落
                String line = paragraph.text();
                lines.add(line);
            }

            for (int lineNumber = 1; lineNumber <= lines.size(); lineNumber++) {
                String line = lines.get(lineNumber - 1);
                if (line.contains(keyword)) {
                    System.out.println("Line " + lineNumber + ": " + line);
                    line = "Line "+lineNumber + ": "+line;
                    result.add(line);
                }
            }

            document.close();
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) throws Exception {
        String filePath = "D:\\upload-directory\\test1.doc";
        String keyword = "9";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            HWPFDocument document = new HWPFDocument(fs);

            Range range = document.getRange(); // 获取整个文档范围

            List<String> lines = new ArrayList<>();
            for (int i = 0; i < range.numParagraphs(); i++) {
                Paragraph paragraph = range.getParagraph(i); // 获取段落
                String line = paragraph.text();
                lines.add(line);
            }

            for (int lineNumber = 1; lineNumber <= lines.size(); lineNumber++) {
                String line = lines.get(lineNumber - 1);
                if (line.contains(keyword)) {
                    System.out.println("Line " + lineNumber + ": " + line);
                }
            }

            document.close();
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
