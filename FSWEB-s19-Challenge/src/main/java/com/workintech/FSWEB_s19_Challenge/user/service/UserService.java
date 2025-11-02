package com.workintech.FSWEB_s19_Challenge.user.service;

import com.workintech.FSWEB_s19_Challenge.user.dto.*;
import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getByUsername(String username);
    User getByIdEntity(Long id); // entity olarak gerektiğinde
}
