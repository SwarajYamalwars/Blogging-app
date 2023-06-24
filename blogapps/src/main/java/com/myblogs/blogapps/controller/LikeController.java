package com.myblogs.blogapps.controller;


import com.myblogs.blogapps.entity.Like;
import com.myblogs.blogapps.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
    @RequestMapping("/likes")
    public class LikeController {
        private final LikeService likeService;

        public LikeController(LikeService likeService) {
            this.likeService = likeService;
        }

        @PostMapping
        public ResponseEntity<Like> createLike(@RequestBody Like like) {
            Like savedLike = likeService.saveLike(like);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLike);
        }

        @GetMapping("/{likeId}")
        public ResponseEntity<Like> getLikeById(@PathVariable Long likeId) {
            Optional<Like> optionalLike = likeService.getLikeById(likeId);
            return optionalLike.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PutMapping("/{likeId}")
        public ResponseEntity<Like> updateLike(@PathVariable Long likeId, @RequestBody Like like) {
            Optional<Like> optionalLike = likeService.getLikeById(likeId);
            if (optionalLike.isPresent()) {
                like.setId(likeId);
                Like savedLike = likeService.saveLike(like);
                return ResponseEntity.ok(savedLike);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{likeId}")
        public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
            boolean deleted = likeService.deleteLikeById(likeId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }



}
