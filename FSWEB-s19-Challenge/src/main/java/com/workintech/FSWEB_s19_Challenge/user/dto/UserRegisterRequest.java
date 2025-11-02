package com.workintech.FSWEB_s19_Challenge.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    @NotBlank @Size(min=3, max=50)
    private String username;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min=6, max=100)
    private String password;

    private String bio;
}
