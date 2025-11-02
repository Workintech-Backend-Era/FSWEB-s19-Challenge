package com.workintech.FSWEB_s19_Challenge.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.workintech.FSWEB_s19_Challenge.user.dto.*;
import com.workintech.FSWEB_s19_Challenge.user.mapper.UserMapper;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;
import com.workintech.FSWEB_s19_Challenge.exception.ResourceNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.workintech.FSWEB_s19_Challenge.security.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(request.getEmail()))) {
            throw new IllegalArgumentException("Email already used");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .bio(request.getBio())
                .build();

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authToken);

        // generate token
        String token = jwtUtil.generateToken(authToken);
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found after authentication"));
        return AuthResponse.builder().token(token).username(user.getUsername()).userId(user.getId()).build();
    }

    @Override
    public UserResponse getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public User getByIdEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    //cümle: 1. Yeni Entity: Follower, follower düzenlenecek
}
