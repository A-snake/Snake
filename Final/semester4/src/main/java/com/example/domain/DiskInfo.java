package com.example.domain;

import lombok.Data;

@Data
public class DiskInfo {
    private String name;
    private Long id;
    public DiskInfo(String name, Long id){
        this.name = name;
        this.id = id;
    }
}
