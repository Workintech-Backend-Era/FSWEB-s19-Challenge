package com.workintech.FSWEB_s19_Challenge.retweet.service;

import java.util.List;

import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetRequest;
import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetResponse;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface RetweetService {
    RetweetResponse createRetweet(User user, RetweetRequest request);
    List<RetweetResponse> getAllByUser(User user);
    RetweetResponse replaceRetweet(Long id, RetweetRequest request, User user); // PUT
    RetweetResponse patchRetweet(Long id, RetweetRequest request, User user);   // PATCH
    void deleteRetweet(Long id, User user);
}
