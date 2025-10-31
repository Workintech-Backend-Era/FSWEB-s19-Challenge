package com.workintech.FSWEB_s19_Challenge.like.security;

import org.springframework.stereotype.Component;

import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;

    public User getCurrentUser(Authentication authentication){
        if(authentication==null || authentication.getName()==null){
            throw new RuntimeException("Authentication is failed");
        }

        return userRepository.findByUsername(authentication.getName()).orElseThrow(()->new RuntimeException("Not Found"));
    }
}
