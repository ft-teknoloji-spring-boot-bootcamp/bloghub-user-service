package com.patika.bloghubuserservice.repository;

import com.patika.bloghubuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //@Query(value = "select * from user where email=?", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
