package com.workintech.FSWEB_s19_Challenge.comment.dto;

import java.time.ZonedDateTime;
import java.util.Set;

public record CommentResponseDto(
        Long id,
        String username,
        Long tweetId,
        String text,
        ZonedDateTime createdAt,
        Set<Long> replyIds,
        int likeCount) {

}
