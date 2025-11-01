package com.workintech.FSWEB_s19_Challenge.like.service;

import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface LikeService {
    void toggleTweetLike(Long tweetId, User user);
    void toggleCommentLike(Long commentId, User user);
    long getTweetLikeCount(Long tweetId);
    long getCommentLikeCount(Long commentId);
    void deleteTweetLike(Long tweetId, User user);
    void deleteCommentLike(Long commentId, User user);
}
