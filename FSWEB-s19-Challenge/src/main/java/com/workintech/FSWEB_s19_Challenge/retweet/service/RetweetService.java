package com.workintech.FSWEB_s19_Challenge.retweet.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.retweet.model.Retweet;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface RetweetService extends JpaRepository<Retweet, Long>{
    Retweet retweet(User user, Tweet originalTweet, String comment);
    void undoRetweet(Long retweetId);
}
