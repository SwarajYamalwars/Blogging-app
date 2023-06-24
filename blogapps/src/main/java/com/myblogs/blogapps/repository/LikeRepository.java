package com.myblogs.blogapps.repository;

import com.myblogs.blogapps.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository  extends JpaRepository<Long, Like> {
    static void save(Like like) {
    }

    void deleteById(Long likeId);

    Optional<Like> findById(Long likeId);

    List<Like> findByPostId(Long postId);
}
