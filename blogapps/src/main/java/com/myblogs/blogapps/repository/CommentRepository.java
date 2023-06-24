package com.myblogs.blogapps.repository;

import com.myblogs.blogapps.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findByPostId(long postId);
   //this is a custom method because this method find post by id is nit present in the repo;


}
