package com.workintech.FSWEB_s19_Challenge.comment.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workintech.FSWEB_s19_Challenge.comment.dto.CommentResponseDto;
import com.workintech.FSWEB_s19_Challenge.comment.mapper.CommentMapper;
import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;
import com.workintech.FSWEB_s19_Challenge.comment.repository.CommentRepository;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.tweet.repository.TweetRepository;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    private User getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @Override
    public void deleteComment(Long id) {
        User currentUser = getAuthenticatedUser();
        Comment existing = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not Found"));

        boolean isOwnComment = existing.getUser().getId().equals(currentUser.getId());
        boolean isOwnTweet = existing.getTweet().getUser().getId().equals(currentUser.getId());

        if (!isOwnComment && !isOwnTweet) {
            throw new RuntimeException("You are not allowed to delete this comment.");
        }

        commentRepository.delete(existing);
    }

    @Override
    public CommentResponseDto create(Comment comment) {
        User currentUser = getAuthenticatedUser();

        Tweet tweet = tweetRepository.findById(comment.getTweet().getId())
                                        .orElseThrow(()->new RuntimeException("Tweet Not Found"));
        comment.setUser(currentUser);
        comment.setTweet(tweet);

        if(comment.getParentComment()!=null && comment.getParentComment().getId()!=null){
            Comment parent = commentRepository.findById(comment.getParentComment().getId())
                                                .orElseThrow(()->new RuntimeException("Parent comment not found"));
            comment.setParentComment(parent);
        }

        return CommentMapper.toDto(commentRepository.save(comment));

    }

    @Override
    public CommentResponseDto update(Long id, Comment comment) {
        User user = getAuthenticatedUser();
        Comment existing = commentRepository.findById(id).orElse(null);

        if(!existing.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are not allowed to update this comment.");
        }
        existing.setText(comment.getText());

        return CommentMapper.toDto(commentRepository.save(existing));

    }

    @Override
    public CommentResponseDto partialUpdate(Long id, Comment comment) {
        User user = getAuthenticatedUser();
        Comment existing = commentRepository.findById(id).orElseThrow(()->new RuntimeException("Comment not found"));

        if(!existing.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are not allowed to update this comment.");
        }

        if(comment.getText() != null) existing.setText(comment.getText());
        return CommentMapper.toDto(commentRepository.save(existing));
    }

    @Override
    public List<CommentResponseDto> findByTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(()->new RuntimeException("Tweet not Found"));
        return commentRepository.findByTweet(tweet).stream().map(CommentMapper::toDto).toList();

    }



}
