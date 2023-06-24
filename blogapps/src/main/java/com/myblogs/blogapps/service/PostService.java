package com.myblogs.blogapps.service;

import com.myblogs.blogapps.payload.PostDto;
import com.myblogs.blogapps.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePost(long id);
}
