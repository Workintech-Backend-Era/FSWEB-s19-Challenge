package com.workintech.FSWEB_s19_Challenge.comment.service;

import java.util.List;

import com.workintech.FSWEB_s19_Challenge.comment.dto.CommentResponseDto;
import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface CommentService {

    void deleteComment(Long id);
    CommentResponseDto create(Comment comment);
    CommentResponseDto update(Long id, Comment comment);
    CommentResponseDto partialUpdate(Long id, Comment comment);
    List<CommentResponseDto> findByTweet(Long tweetId);
}
