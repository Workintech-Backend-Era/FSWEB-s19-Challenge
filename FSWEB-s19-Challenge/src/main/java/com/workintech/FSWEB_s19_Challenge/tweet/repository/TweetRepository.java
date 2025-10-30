package com.workintech.FSWEB_s19_Challenge.tweet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;


public interface TweetRepository extends JpaRepository<Tweet, Long>{

    List<Tweet> findByUser(User user);
    List<Tweet> findAllByOrderByCreatedAtDesc();
}
