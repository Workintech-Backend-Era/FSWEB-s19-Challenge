package com.workintech.FSWEB_s19_Challenge.like.service;

import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface LikeService {
    TweetLike toggleTweetLike(Long tweetId, User user);
    CommentLike toggleCommentLike(Long commentId, User user);
    long getTweetLikeCount(Long tweetId);
    long getCommentLikeCount(Long commentId);
    void deleteTweetLike(Long tweetId, User user);
    void deleteCommentLike(Long commentId, User user);
}
