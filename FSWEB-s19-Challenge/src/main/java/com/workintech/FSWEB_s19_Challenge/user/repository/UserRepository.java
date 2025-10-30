package com.workintech.FSWEB_s19_Challenge.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.FSWEB_s19_Challenge.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
