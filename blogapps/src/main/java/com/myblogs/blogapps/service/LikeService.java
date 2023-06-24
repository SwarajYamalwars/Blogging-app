package com.myblogs.blogapps.service;
import com.myblogs.blogapps.entity.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {
        Like saveLike(Like like);

        Optional<Like> getLikeById(Long likeId);
    Like updateLike(Long likeId, Like like);
        boolean deleteLikeById(Long likeId);


    List<Like> getLikesByPostId(Long postId);
}
