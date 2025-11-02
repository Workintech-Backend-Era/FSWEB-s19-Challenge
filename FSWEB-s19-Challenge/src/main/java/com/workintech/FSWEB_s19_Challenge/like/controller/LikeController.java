package com.workintech.FSWEB_s19_Challenge.like.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.security.JwtUtil;
import com.workintech.FSWEB_s19_Challenge.like.dto.LikeResponseDto;
import com.workintech.FSWEB_s19_Challenge.like.mapper.LikeMapper;
import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;
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
    public ResponseEntity<LikeResponseDto> toggleTweetLike(@PathVariable Long tweetId, Authentication authentication){
        User currentUser = jwtUtil.getCurrentUser(authentication);
        TweetLike like = likeService.toggleTweetLike(tweetId, currentUser);
        return ResponseEntity.ok(LikeMapper.toDto(like));
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<LikeResponseDto> toggleCommentLike(@PathVariable Long commentId, Authentication authentication){
        User currentUser = jwtUtil.getCurrentUser(authentication);
        CommentLike like = likeService.toggleCommentLike(commentId, currentUser);
        return ResponseEntity.ok(LikeMapper.toDto(like));
    }

    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<Long> getTweetLikeCount(@PathVariable Long tweetId){
        return ResponseEntity.ok(likeService.getTweetLikeCount(tweetId));
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Long> getCommentLikeCount(@PathVariable Long commentId){
        return ResponseEntity.ok(likeService.getCommentLikeCount(commentId));
    }

    @PutMapping("/comment/{commentId}")
    public ResponseEntity<String> likeCommentPut(@PathVariable Long commentId, Authentication auth){
        User user = jwtUtil.getCurrentUser(auth);
        likeService.toggleCommentLike(commentId, user);
        return ResponseEntity.ok("Comment Process has been done.");
    }

    @PutMapping("tweet/{tweetId}")
    public ResponseEntity<String> likeTweetPut(@PathVariable Long tweetId, Authentication auth){
        User user = jwtUtil.getCurrentUser(auth);
        likeService.toggleTweetLike(tweetId, user);
        return ResponseEntity.ok("Tweet Process has been done.");
    }

    @DeleteMapping("/tweet/{tweetId}")
    public ResponseEntity<String> deleteTweetLike(@PathVariable Long tweetId,Authentication auth){
        User user = jwtUtil.getCurrentUser(auth);
        likeService.deleteTweetLike(tweetId,user);
        return ResponseEntity.ok("Tweet Like was deleted.");
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteCommentLike(@PathVariable Long commentId, Authentication auth){
        User user = jwtUtil.getCurrentUser(auth);
        likeService.deleteCommentLike(commentId, user);
        return ResponseEntity.ok("Comment Like was deleted");
    }



}
