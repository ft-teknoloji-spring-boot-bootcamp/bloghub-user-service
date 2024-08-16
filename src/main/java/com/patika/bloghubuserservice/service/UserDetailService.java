package com.patika.bloghubuserservice.service;

import com.patika.bloghubuserservice.exception.BlogHubException;
import com.patika.bloghubuserservice.exception.ExceptionMessages;
import com.patika.bloghubuserservice.model.User;
import com.patika.bloghubuserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> User.builder().email(user.getEmail())
                        .password(user.getPassword())
                        .build())
                .orElseThrow(() -> new BlogHubException(ExceptionMessages.USER_NOT_FOUND));
    }
}
