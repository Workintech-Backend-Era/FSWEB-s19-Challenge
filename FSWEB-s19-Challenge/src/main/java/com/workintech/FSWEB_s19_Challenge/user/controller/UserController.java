package com.workintech.FSWEB_s19_Challenge.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.workintech.FSWEB_s19_Challenge.user.dto.UserResponse;
import com.workintech.FSWEB_s19_Challenge.user.service.UserService;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // get current logged-in user
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(userService.getByUsername(currentUser.getUsername()));
    }

    // get user by username (public)
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }
}
