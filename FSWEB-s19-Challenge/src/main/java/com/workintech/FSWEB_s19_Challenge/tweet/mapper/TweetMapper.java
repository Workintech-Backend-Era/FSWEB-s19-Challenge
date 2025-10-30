package com.workintech.FSWEB_s19_Challenge.tweet.mapper;

import org.springframework.stereotype.Component;

import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetRequestDto;
import com.workintech.FSWEB_s19_Challenge.tweet.dto.TweetResponseDto;
import com.workintech.FSWEB_s19_Challenge.tweet.model.Tweet;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

@Component
public class TweetMapper {

    public Tweet toEntity(TweetRequestDto dto){
        if(dto==null)
            return null;

        Tweet tweet = Tweet.builder()
                        .user(dto.user())
                        .content(dto.content()).build();
        return tweet;
    }

    public TweetResponseDto toResponseDto(Tweet tweet){
        if(tweet==null)
            return null;

        return new TweetResponseDto(tweet.getUser(), tweet.getContent());
    }

}
