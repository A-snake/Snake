package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFToText {
    public static void main(String[] args) throws IOException {
        // Load PDF document
        PDDocument document = PDDocument.load(new File("D:\\OneDrive\\桌面\\大学\\操作系统复习\\操作系统复习\\操作系统-样卷B.pdf"));

        // Create PDF stripper
        PDFTextStripper stripper = new PDFTextStripper();

        // Get text from PDF document
        String text = stripper.getText(document);

        // Write text to file
        FileWriter writer = new FileWriter("D:\\test1\\test4.txt");
        writer.write(text);
        writer.close();

        // Close PDF document
        document.close();
    }
}
