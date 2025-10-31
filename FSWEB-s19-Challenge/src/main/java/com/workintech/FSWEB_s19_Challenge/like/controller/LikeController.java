package com.workintech.FSWEB_s19_Challenge.like.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.FSWEB_s19_Challenge.like.security.JwtUtil;
import com.workintech.FSWEB_s19_Challenge.like.service.LikeService;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/tweet/{tweetId}")
    public ResponseEntity<String> toggleTweetLike(@PathVariable Long tweetId, Authentication authentication){
        User currentUser = jwtUtil.getCurrentUser(authentication);
        likeService.toggleTweetLike(tweetId, currentUser);
        return ResponseEntity.ok("TWeet like processing has been done.");
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<String> toggleCommentLike(@PathVariable Long commentId, Authentication authentication){
        User currentUser = jwtUtil.getCurrentUser(authentication);
        likeService.toggleCommentLike(commentId, currentUser);
        return ResponseEntity.ok("Comment like processing has been done.");
    }

    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<Long> getTweetLikeCount(@PathVariable Long tweetId){
        return ResponseEntity.ok(likeService.getTweetLikeCount(tweetId));
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Long> getCommentLikeCount(@PathVariable Long commentId){
        return ResponseEntity.ok(likeService.getCommentLikeCount(commentId));
    }

    //PutMappingden devam edeceğim

}
