package com.java.test_java.service;

import com.java.test_java.dto.PageResponse;
import com.java.test_java.dto.PostDto;
import com.java.test_java.entity.Post;
import com.java.test_java.exception.InvalidPaginationException;
import com.java.test_java.factory.PostFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final RestTemplate  restTemplate = new RestTemplate();
    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts";


    public PageResponse<PostDto> getPaginatedPosts(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new InvalidPaginationException("Page must be >= 0 and size must be > 0");
        }

        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);

        if (posts == null) {
            throw new RuntimeException("Failed to fetch posts");
        }

        List<PostDto> mapped = Arrays.stream(posts)
                .map(PostFactory::create)
                .collect(Collectors.toList());

        int totalElements = mapped.size();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        if (fromIndex >= totalElements) {
            throw new InvalidPaginationException("Page out of bounds");
        }

        List<PostDto> pageContent = mapped.subList(fromIndex, toIndex);
        return new PageResponse<>(pageContent, page, size, totalElements);
    }
}
