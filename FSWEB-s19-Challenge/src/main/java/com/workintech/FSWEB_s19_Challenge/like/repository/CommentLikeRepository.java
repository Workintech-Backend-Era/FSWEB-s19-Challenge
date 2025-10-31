package com.workintech.FSWEB_s19_Challenge.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long>{
    long countByComment(Comment comment);
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
