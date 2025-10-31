package com.workintech.FSWEB_s19_Challenge.comment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.FSWEB_s19_Challenge.comment.dto.CommentResponseDto;
import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> create(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.create(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id ,@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.update(id,comment));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> partialUpdate(@PathVariable Long id, @RequestBody Comment comment){
        return ResponseEntity.ok(commentService.partialUpdate(id,comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<List<CommentResponseDto>> findByTweet(@PathVariable Long tweetId){
        return ResponseEntity.ok(commentService.findByTweet(tweetId));
    }



}
