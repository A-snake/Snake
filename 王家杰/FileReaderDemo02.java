package org.example;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FileReaderDemo02 {
    private static String strFinal ="";
    public static void main(String[] args) throws Exception {
       //Reader fr =new FileReader("D:\\OneDrive\\桌面\\大学\\操作系统复习\\王家杰CPU调度算法.doc");
        //String route = "D:\\Java\\semester\\src\\document\\cpu.txt";
        String route  = "D:\\test1\\test1.txt";
        String route2 = "D:\\test1\\test4.txt";
//        Reader fr =new FileReader("D:\\Java\\semester\\src\\document\\cpu.txt");
//        char[] buffer = new char[1024];
//        int len;
//        while ((len = fr.read(buffer)) != -1){
//            System.out.println(new String(buffer,0,len));
//        }
//        BufferedReader in = new BufferedReader(new FileReader("D:\\Java\\semester\\src\\document\\cpu.txt"));
//
//
//        PrintWriter out = new PrintWriter(new FileWriter("D:\\Java\\semester\\src\\document\\cpu.txt"));

       Text obj = new Text();
//        obj.open(route);
//        obj.save("D:\\test1\\test2.txt");
//        obj.search("D:\\test1\\test2.txt","D:\\test1\\test3.txt");


        Text obj2 = new Text();
        obj2.open(route2);
        obj2.save("D:\\test1\\test4.txt");
        obj2.search("D:\\test1\\test4.txt","D:\\test1\\test5.txt");

    }
}
