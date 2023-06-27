package org.example;

import java.io.*;
import java.util.Scanner;

public class Text {
    private String strFinal = "";
    public void open(String fileName) {
                 try {
                       BufferedReader in = new BufferedReader(new FileReader(fileName));
                        String s = null;

                        while ((s = in.readLine()) != null) {
                               strFinal = strFinal + s + "\n";
                            }
                        in.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
             }
    public void save(String fileName) {
//        System.out.println("请输入要匹配的关键字");
//        Scanner sc =  new Scanner(System.in);
//        String keywords = sc.nextLine();
        try {
            BufferedReader in = new BufferedReader(new StringReader(strFinal));
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            int lineCount = 1;
            String s = null;
            while ((s = in.readLine()) != null) {
//                            if(s.contains(keywords)){
                out.println(lineCount++ + "\t" + s);
//                                out.println(s);
//                            }
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    public void search(String fileName,String fileName2) throws IOException {
        System.out.println("请输入要匹配的关键字");
        Scanner sc =  new Scanner(System.in);
        String keywords = sc.nextLine();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            PrintWriter out = new PrintWriter(new FileWriter(fileName2));

            String s = null;
            while ((s = in.readLine()) != null) {
                if (s.contains(keywords)) {
                    System.out.println(s);
                    out.println(s);
                }
            }
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
