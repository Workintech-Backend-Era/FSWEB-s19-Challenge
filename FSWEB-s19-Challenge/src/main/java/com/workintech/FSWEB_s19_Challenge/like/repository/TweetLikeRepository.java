package com.workintech.FSWEB_s19_Challenge.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {
    long countByTweet(Tweet tweet);
    Optional<TweetLike> findByUserAndTweet(User user, Tweet tweet);
}
