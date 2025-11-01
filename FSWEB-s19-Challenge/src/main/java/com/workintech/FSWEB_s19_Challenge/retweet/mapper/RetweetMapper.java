package com.workintech.FSWEB_s19_Challenge.retweet.mapper;

import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetResponse;
import com.workintech.FSWEB_s19_Challenge.retweet.model.Retweet;

public class RetweetMapper {

    public static RetweetResponse toResponse(Retweet retweet){
        return RetweetResponse.builder()
                              .id(retweet.getId())
                              .userId(retweet.getUser().getId())
                              .originalTweetId(retweet.getOriginalTweet().getId())
                              .comment(retweet.getComment())
                              .createdAt(retweet.getCreatedAt())
                              .build();
    }

}
