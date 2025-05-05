package com.java.test_java.factory;

import com.java.test_java.dto.PostDto;
import com.java.test_java.entity.Post;

public class PostFactory {

    public static PostDto create(Post post) {
        return new PostDto(post.getId(), post.getTitle());
    }
}
