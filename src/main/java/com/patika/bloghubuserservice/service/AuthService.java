package com.patika.bloghubuserservice.service;

import com.patika.bloghubuserservice.dto.request.UserLoginRequest;
import com.patika.bloghubuserservice.dto.request.UserSaveRequest;
import com.patika.bloghubuserservice.exception.BlogHubException;
import com.patika.bloghubuserservice.exception.ExceptionMessages;
import com.patika.bloghubuserservice.model.Role;
import com.patika.bloghubuserservice.model.User;
import com.patika.bloghubuserservice.model.enums.StatusType;
import com.patika.bloghubuserservice.model.enums.UserType;
import com.patika.bloghubuserservice.repository.RoleRepository;
import com.patika.bloghubuserservice.repository.UserRepository;
import com.patika.bloghubuserservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public void register(UserSaveRequest request) {

        boolean isExists = userRepository.existsByEmail(request.getEmail());

        if (isExists) {
            throw new BlogHubException(ExceptionMessages.USER_ALREADY_DEFINED);
        }

        Role adminRole = roleRepository.findAll()
                .stream()
                .filter(role -> role.getName().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new BlogHubException(ExceptionMessages.ROLE_NOT_FOUND));

        User user = User.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .userType(UserType.STANDARD)
                .statusType(StatusType.APPROVED)
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(user);
    }

    public String login(UserLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BlogHubException(ExceptionMessages.USER_NOT_FOUND));

        return jwtUtil.generateToken(user);

    }
}