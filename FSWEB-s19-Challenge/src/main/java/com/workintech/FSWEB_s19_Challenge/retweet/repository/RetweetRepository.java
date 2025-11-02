package com.workintech.FSWEB_s19_Challenge.retweet.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.workintech.FSWEB_s19_Challenge.retweet.model.Retweet;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByUserAndOriginalTweet(User user, Tweet originalTweet);
    List<Retweet> findAllByUser(User user);
}
