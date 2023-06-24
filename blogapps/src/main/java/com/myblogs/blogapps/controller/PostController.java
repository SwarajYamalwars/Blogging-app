package com.myblogs.blogapps.controller;

import com.myblogs.blogapps.payload.PostDto;
import com.myblogs.blogapps.payload.PostResponse;
import com.myblogs.blogapps.service.PostService;
import com.myblogs.blogapps.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){


            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
    //localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=title

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam (value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam( value = "sortBy",defaultValue =AppConstants.DEFAULT_SORT_BY ,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIR,required=false)String sortDir
    ){
      return postService.getAllPosts(  pageNo, pageSize,sortBy,sortDir);

    }//http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){

        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new  ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable ("id") long id){
        postService.deletePost(id);
        return new  ResponseEntity<>("Post entity deleted sucessfully.",HttpStatus.OK);
    }
}
