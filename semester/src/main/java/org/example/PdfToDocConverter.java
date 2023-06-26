package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;


public class PdfToDocConverter {
    public static void main(String[] args) throws IOException {
        // 读取PDF文件
        PDDocument pdfDoc = PDDocument.load(new FileInputStream("D:\\OneDrive\\桌面\\大学\\操作系统复习\\操作系统复习\\操作系统-样卷B.pdf"));

        // 提取PDF文本
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(pdfDoc);


        FileInputStream fis = new FileInputStream("D:\\OneDrive\\桌面\\大学\\操作系统复习\\操作系统复习\\操作系统-样卷B.pdf");
        HWPFDocument doc = new HWPFDocument(fis);

        // 创建DOC文件
        //HWPFDocument doc = new HWPFDocument();
        Range range = doc.getRange();
        range.insertAfter(text);

        // 保存DOC文件
        FileOutputStream out = new FileOutputStream(new File("D:\\test1\\test1.doc"));
        doc.write(out);
        out.close();

        // 关闭PDF文件
        pdfDoc.close();
    }

}
