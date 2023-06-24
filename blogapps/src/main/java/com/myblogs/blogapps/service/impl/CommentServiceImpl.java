package com.myblogs.blogapps.service.impl;

import com.myblogs.blogapps.entity.Comment;
import com.myblogs.blogapps.entity.Post;
import com.myblogs.blogapps.exception.ResourceNotFoundException;
import com.myblogs.blogapps.payload.CommentDto;
import com.myblogs.blogapps.repository.CommentRepository;
import com.myblogs.blogapps.repository.PostRepository;
import com.myblogs.blogapps.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;


    }

    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
      return   mapToDto(newComment);


    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
      return   comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());


    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
        Post post= postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post","id",postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id)
        );

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(comment.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("postId","id",commentId)
        );
        commentRepository.deleteById(commentId);
    }

    Comment mapToComment(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);

//        Comment comment= new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return comment;


    }
    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        return commentDto;
    }
}
