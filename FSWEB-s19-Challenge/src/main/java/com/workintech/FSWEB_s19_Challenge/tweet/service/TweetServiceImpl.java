package com.workintech.FSWEB_s19_Challenge.tweet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetResponseDto;
import com.workintech.FSWEB_s19_Challenge.tweet.mapper.TweetMapper;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.tweet.repository.TweetRepository;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;

    private final TweetMapper tweetMapper;

    @Override
    public Tweet createTweet(User user, String content) {

        Tweet tweet = Tweet
                            .builder()
                            .user(user)
                            .content(content)
                            .build();

        return tweetRepository.save(tweet);

    }

    @Override
    public List<Tweet> getAllTweets() {

        return tweetRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Tweet> getTweetsByUser(User user) {

        return tweetRepository.findByUser(user);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetRepository.deleteById(id);
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {

        return tweetRepository
                    .findById(id).map(tweetMapper::toResponseDto).orElse(null);
    }

    @Override
    public TweetResponseDto updateTweet(Long id, Tweet tweet) {

        Tweet existing = tweetRepository.findById(id).orElse(null);

        //Put ve patch metodlarından devam
    }

}
