package com.workintech.FSWEB_s19_Challenge.follower.controller;

import com.workintech.FSWEB_s19_Challenge.user.dto.UserResponse;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.follower.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followService;

    // Takip et
    @PostMapping("/{username}")
    public ResponseEntity<String> follow(@AuthenticationPrincipal User currentUser,
                                         @PathVariable String username) {
        followService.followUser(currentUser, username);
        return ResponseEntity.ok("@" + username + " kullanıcısı takip edildi.");
    }

    // Takipten çık
    @DeleteMapping("/{username}")
    public ResponseEntity<String> unfollow(@AuthenticationPrincipal User currentUser,
                                           @PathVariable String username) {
        followService.unfollowUser(currentUser, username);
        return ResponseEntity.ok("@" + username + " kullanıcısı takipten çıkarıldı.");
    }

    // Kullanıcının takipçilerini getir
    @GetMapping("/{username}/followers")
    public ResponseEntity<List<UserResponse>> getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(followService.getFollowers(username));
    }

    // Kullanıcının takip ettiklerini getir
    @GetMapping("/{username}/following")
    public ResponseEntity<List<UserResponse>> getFollowing(@PathVariable String username) {
        return ResponseEntity.ok(followService.getFollowing(username));
    }
}
