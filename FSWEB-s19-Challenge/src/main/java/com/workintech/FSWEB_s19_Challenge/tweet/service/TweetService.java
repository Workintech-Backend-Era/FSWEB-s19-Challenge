package com.workintech.FSWEB_s19_Challenge.tweet.service;

import java.util.List;

import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetResponseDto;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface TweetService {
    Tweet createTweet(User user, String content);
    List<Tweet> getAllTweets();
    List<Tweet> getTweetsByUser(User user);
    void deleteTweet(Long id);
    TweetResponseDto getTweetById(Long id);
    TweetResponseDto updateTweet(Long id, Tweet tweet);
}
