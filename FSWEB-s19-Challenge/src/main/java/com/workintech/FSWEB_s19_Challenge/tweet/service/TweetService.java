package com.workintech.FSWEB_s19_Challenge.tweet.service;

import java.util.List;

import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetResponseDto;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface TweetService {
    TweetResponseDto createTweet(User user, String content);
    List<TweetResponseDto> getAllTweets();
    List<Tweet> getTweetsByUser(User user);
    void deleteTweet(Long id, User user);
    TweetResponseDto getTweetById(Long id);
    TweetResponseDto updateTweet(Long id, Tweet tweet, User user);
    TweetResponseDto partialUpdateTweet(Long id, Tweet tweet, User currentUser);
}
