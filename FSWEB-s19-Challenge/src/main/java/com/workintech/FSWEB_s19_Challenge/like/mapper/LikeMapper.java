package com.workintech.FSWEB_s19_Challenge.like.mapper;

import com.workintech.FSWEB_s19_Challenge.like.dto.LikeResponseDto;
import com.workintech.FSWEB_s19_Challenge.like.model.CommentLike;
import com.workintech.FSWEB_s19_Challenge.like.model.TweetLike;

public class LikeMapper {

    public static LikeResponseDto toDto(TweetLike like) {
        return LikeResponseDto.builder()
                .id(like.getId())
                .userId(like.getUser().getId())
                .targetId(like.getTweet().getId())
                .targetType("TWEET")
                .createdAt(like.getCreatedAt())
                .build();
    }

    public static LikeResponseDto toDto(CommentLike like) {
        return LikeResponseDto.builder()
                .id(like.getId())
                .userId(like.getUser().getId())
                .targetId(like.getComment().getId())
                .targetType("COMMENT")
                .createdAt(like.getCreatedAt())
                .build();
    }
}
