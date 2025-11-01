package com.workintech.FSWEB_s19_Challenge.retweet.dto;

import java.time.ZonedDateTime;

import lombok.Builder;

@Builder
public record RetweetResponse(
        Long id,
        Long userId,
        Long originalTweetId,
        String comment,
        ZonedDateTime createdAt
) {

}
