package org.example;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        while(true) {
            System.out.println("1、按关键字检索文件");
           // System.out.println("2、按后缀名找出文件");
            System.out.println("3、退出");
            System.out.println("请选择你的操作");
            Scanner sr = new Scanner(System.in);
            int choose = sr.nextInt();
            if(choose==1) {
                ArrayList<String> strings = searchByKey();
                System.out.println("在choose1中选择");
                for (String string : strings) {
                    System.out.println(string);
                }
            }else if(choose==2) {
                searchBySuffix();
            }else if(choose==3) {
                exit();
            }
        }
    }




    //退出
    public static void exit() {
        System.out.println("你已经退出系统，感谢使用！");
        System.exit(0);
    }


    //按照关键字查找
    public static ArrayList<String> searchByKey() {
        ArrayList<String> list = new ArrayList<>();

        Scanner sr = new Scanner(System.in);
        System.out.println("请输入要检索文件的位置：");
        String dirPath = sr.next();
        File dir = new File(dirPath);
        File[]files = dir.listFiles();
        String[] fileNames = new String[files.length];
        System.out.println("请输入检索文件关键字");
        String mainName = sr.next();
        //(dir.getName()+"/"+mainName);
        Filter filter = new Filter();
        for(int i=0;i<files.length;i++) {
            fileNames[i] = files[i].getName();
            if(filter.acceptLikeName(fileNames[i],mainName)) {

                list.add(files[i].getAbsolutePath());

                //System.out.println("返回由此File表示的文件或目录的名称："+fileNames[i]);
                //System.out.println("返回此File的绝对路径字符串:"+files[i].getAbsolutePath());
              //  System.out.println("返回由此File表示的文件的长度:"+files[i].length());
            }
        }
        return list;

    }



    //按照后缀名查找
    public static void searchBySuffix() {
        Scanner sr = new Scanner(System.in);
        System.out.println("请输入要检索文件的位置：");
        String dirPath = sr.next();
        File dir = new File(dirPath);
        File[]files = dir.listFiles();
        String[] fileNames = new String[files.length];
        Filter filter = new Filter();
        System.out.println("请输入后缀名");
        String endName = sr.next();

        for(int i=0;i<files.length;i++) {
            fileNames[i] = files[i].getName();
            if(filter.acceptEndName(dir,fileNames[i],endName)) {
                System.out.println("后缀名为"+endName+"的有："+fileNames[i]);
            }
        }
    }
}

//继承类FilenameFileter
class Filter implements FilenameFilter {
    public boolean accept(File dir,String name) {

        return true;
    }
    public boolean acceptLikeName(String name,String mainName) {
        if(name.contains(mainName)) {
            return true;
        }
        return false;
    }
    public boolean acceptEndName(File dir,String name,String endName) {
        File file = new File(dir,name);
        if(file.isFile()&&name.endsWith(endName)) {
            return true;
        }
        return false;
    }

}

