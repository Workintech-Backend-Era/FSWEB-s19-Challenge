package com.workintech.FSWEB_s19_Challenge.user.mapper;

import com.workintech.FSWEB_s19_Challenge.user.dto.UserResponse;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
