package com.java.test_java.controller;

import com.java.test_java.dto.PageResponse;
import com.java.test_java.dto.PostDto;
import com.java.test_java.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public PageResponse<PostDto> getPost(@RequestParam (defaultValue = "0") int page,
                                         @RequestParam (defaultValue = "10") int size) {
        return postService.getPaginatedPosts(page, size);
    }
}
