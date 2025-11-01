package com.workintech.FSWEB_s19_Challenge.retweet.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetRequest;
import com.workintech.FSWEB_s19_Challenge.retweet.dto.RetweetResponse;
import com.workintech.FSWEB_s19_Challenge.retweet.service.RetweetService;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/retweets")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<RetweetResponse> createRetweet(@AuthenticationPrincipal User user,
                                                         @RequestBody RetweetRequest request) {
        return ResponseEntity.ok(retweetService.createRetweet(user, request));
    }

    @GetMapping
    public ResponseEntity<List<RetweetResponse>> getAllByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(retweetService.getAllByUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RetweetResponse> replaceRetweet(@PathVariable Long id,
                                                          @RequestBody RetweetRequest request,
                                                          @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(retweetService.replaceRetweet(id, request, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RetweetResponse> updateRetweetPartially(@PathVariable Long id,
                                                                  @RequestBody RetweetRequest request,
                                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(retweetService.patchRetweet(id, request, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable Long id,
                                              @AuthenticationPrincipal User user) {
        retweetService.deleteRetweet(id, user);
        return ResponseEntity.noContent().build();
    }
}
