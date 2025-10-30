package com.workintech.FSWEB_s19_Challenge.tweet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetResponseDto;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.tweet.service.TweetService;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody String content){
        User user = getCurrentUser();
        return ResponseEntity.ok(tweetService.createTweet(user, content));
    }

    @GetMapping
    public ResponseEntity<List<Tweet>> getAllTweets(){
        return ResponseEntity.ok(tweetService.getAllTweets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetResponseDto> getTweetById(@PathVariable Long id){
        return ResponseEntity.ok(tweetService.getTweetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TweetResponseDto> updateTweet(@PathVariable Long id, @RequestBody Tweet tweet){
        return ResponseEntity.ok(tweetService.updateTweet(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id){
        tweetService.deleteTweet(id);
        return ResponseEntity.noContent().build();
    }
}
