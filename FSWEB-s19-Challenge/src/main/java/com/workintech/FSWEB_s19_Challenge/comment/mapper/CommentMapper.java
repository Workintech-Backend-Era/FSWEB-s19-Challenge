package com.workintech.FSWEB_s19_Challenge.comment.mapper;

import java.util.stream.Collectors;

import com.workintech.FSWEB_s19_Challenge.comment.dto.CommentResponseDto;
import com.workintech.FSWEB_s19_Challenge.comment.model.Comment;

public class CommentMapper {

    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(
            comment.getId(),
            comment.getUser().getUsername(),
            comment.getTweet().getId(),
            comment.getText(),
            comment.getCreatedAt(),
            comment.getReplies().stream().map(Comment::getId).collect(Collectors.toSet()),
            comment.getLikes().size()
        );
    }
}
