package com.workintech.FSWEB_s19_Challenge.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import java.util.List;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;


public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByTweet(Tweet tweet);
}
