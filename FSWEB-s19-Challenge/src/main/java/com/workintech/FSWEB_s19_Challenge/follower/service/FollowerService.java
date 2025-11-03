package com.workintech.FSWEB_s19_Challenge.follower.service;

import com.workintech.FSWEB_s19_Challenge.user.dto.UserResponse;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import java.util.List;

public interface FollowerService {
    void followUser(User currentUser, String usernameToFollow);
    void unfollowUser(User currentUser, String usernameToUnfollow);
    List<UserResponse> getFollowers(String username);
    List<UserResponse> getFollowing(String username);
}
