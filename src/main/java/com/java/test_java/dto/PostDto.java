package com.java.test_java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private int id;
    private String title;

    public PostDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
