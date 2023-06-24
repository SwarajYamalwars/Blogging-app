package com.myblogs.blogapps.repository;

import com.myblogs.blogapps.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
