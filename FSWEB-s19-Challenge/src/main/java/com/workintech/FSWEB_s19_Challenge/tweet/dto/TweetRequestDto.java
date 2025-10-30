package com.workintech.FSWEB_s19_Challenge.tweet.dto;

import com.workintech.FSWEB_s19_Challenge.user.model.User;

public record TweetRequestDto(User user, String content) {


}
