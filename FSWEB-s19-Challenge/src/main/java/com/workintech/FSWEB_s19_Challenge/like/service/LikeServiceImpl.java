package com.workintech.FSWEB_s19_Challenge.like.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.comment.repository.CommentRepository;
import com.workintech.FSWEB_s19_Challenge.exception.ResourceNotFoundException;
import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;
import com.workintech.FSWEB_s19_Challenge.like.repository.CommentLikeRepository;
import com.workintech.FSWEB_s19_Challenge.like.repository.TweetLikeRepository;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.tweet.repository.TweetRepository;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeServiceImpl implements LikeService {

        private final TweetLikeRepository tweetLikeRepository;
        private final CommentLikeRepository commentLikeRepository;
        private final TweetRepository tweetRepository;
        private final CommentRepository commentRepository;

        @Override
        public TweetLike toggleTweetLike(Long tweetId, User user) {
                Tweet tweet = tweetRepository.findById(tweetId)
                                .orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));

                return tweetLikeRepository.findByUserAndTweet(user, tweet)
                                .map(like -> {
                                        tweetLikeRepository.delete(like);
                                        return like;
                                }).orElseGet(() -> tweetLikeRepository.save(
                                                TweetLike.builder().tweet(tweet).user(user).build()));
        }

        @Override
        public CommentLike toggleCommentLike(Long commentId, User user) {
                Comment comment = commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("Comment not Found"));

                return commentLikeRepository.findByUserAndComment(user, comment)
                                .map(like -> {
                                        commentLikeRepository.delete(like);
                                        return like;
                                }).orElseGet(() -> commentLikeRepository.save(
                                                CommentLike.builder().comment(comment).user(user).build()));
        }

        @Override
        public long getTweetLikeCount(Long tweetId) {
                Tweet tweet = tweetRepository.findById(tweetId)
                                .orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));
                return tweetLikeRepository.countByTweet(tweet);
        }

        @Override
        public long getCommentLikeCount(Long commentId) {
                Comment comment = commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("Comment not Found"));
                return commentLikeRepository.countByComment(comment);
        }

        @Override
        public void deleteTweetLike(Long tweetId, User user) {
                Tweet tweet = tweetRepository.findById(tweetId)
                                .orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));
                tweetLikeRepository.findByUserAndTweet(user, tweet).ifPresent(tweetLikeRepository::delete);
        }

        @Override
        public void deleteCommentLike(Long commentId, User user) {
                Comment comment = commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("Comment not Found"));
                commentLikeRepository.findByUserAndComment(user, comment).ifPresent(commentLikeRepository::delete);
        }

}
