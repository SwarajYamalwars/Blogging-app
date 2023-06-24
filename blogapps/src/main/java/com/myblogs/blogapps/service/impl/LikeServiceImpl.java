package com.myblogs.blogapps.service.impl;


import com.myblogs.blogapps.entity.Like;
import com.myblogs.blogapps.repository.LikeRepository;
import com.myblogs.blogapps.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;


    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }





    @Override
    public Like saveLike(Like like) {
        return null;
    }

    @Override
    public Optional<Like> getLikeById(Long likeId) {
        return likeRepository.findById(likeId);
    }

    @Override
    public Like updateLike(Long likeId, Like like) {
        return null;
    }

    @Override
    public boolean deleteLikeById(Long likeId) {
        Optional<Like> optionalLike = likeRepository.findById(likeId);
        if (optionalLike.isPresent()) {
            likeRepository.deleteById(likeId);
            return true;
        }
        return false;
    }

    @Override
    public List<Like> getLikesByPostId(Long postId) {
        return likeRepository.findByPostId(postId);
    }
}
