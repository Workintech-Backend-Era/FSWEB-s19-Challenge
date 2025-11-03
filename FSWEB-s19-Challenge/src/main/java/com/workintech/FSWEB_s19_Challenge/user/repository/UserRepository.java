package com.workintech.FSWEB_s19_Challenge.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.followers WHERE u.username = :username")
    Optional<User> findByUsernameWithFollowers(@Param("username") String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.followings WHERE u.username = :username")
    Optional<User> findByUsernameWithFollowings(@Param("username") String username);

}
