package com.workintech.FSWEB_s19_Challenge.comment.service;

import java.util.List;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface CommentService {
    Comment addComment(User user, Tweet tweet, String text);
    List<Comment> getCommentsByTweet(Tweet tweet);
    void deleteComment(Long id);
}
