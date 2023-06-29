package com.example.entity;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        DiskExplorer de = new DiskExplorer();
        de.getDirectory(new File("D:\\"));
    }
}
