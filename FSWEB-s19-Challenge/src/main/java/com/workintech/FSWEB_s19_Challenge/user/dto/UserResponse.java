package com.workintech.FSWEB_s19_Challenge.user.dto;

import java.time.ZonedDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private ZonedDateTime createdAt;
}
