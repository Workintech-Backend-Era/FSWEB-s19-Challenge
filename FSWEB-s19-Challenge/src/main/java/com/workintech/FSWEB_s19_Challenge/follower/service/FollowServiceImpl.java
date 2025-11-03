package com.workintech.FSWEB_s19_Challenge.follower.service;

import com.workintech.FSWEB_s19_Challenge.exception.ResourceNotFoundException;
import com.workintech.FSWEB_s19_Challenge.user.dto.UserResponse;
import com.workintech.FSWEB_s19_Challenge.user.mapper.UserMapper;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowerService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void followUser(User currentUser, String usernameToFollow) {
        if (currentUser.getUsername().equals(usernameToFollow)) {
            throw new IllegalArgumentException("Kendinizi takip edemezsiniz!");
        }

        User userToFollow = userRepository.findByUsername(usernameToFollow)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + usernameToFollow));

        currentUser.getFollowings().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);

        userRepository.save(currentUser);
        userRepository.save(userToFollow);
    }

    @Override
    public void unfollowUser(User currentUser, String usernameToUnfollow) {
        User userToUnfollow = userRepository.findByUsername(usernameToUnfollow)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + usernameToUnfollow));

        currentUser.getFollowings().remove(userToUnfollow);
        userToUnfollow.getFollowers().remove(currentUser);

        userRepository.save(currentUser);
        userRepository.save(userToUnfollow);
    }

    @Override
    public List<UserResponse> getFollowers(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + username));
        return user.getFollowers().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getFollowing(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı bulunamadı: " + username));
        return user.getFollowings().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    //React ara yüzünden devam, testler o bitince yapılacak
}
