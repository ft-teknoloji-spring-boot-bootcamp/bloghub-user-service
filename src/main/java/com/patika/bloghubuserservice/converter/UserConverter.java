package com.patika.bloghubuserservice.converter;

import com.patika.bloghubuserservice.dto.response.UserResponse;
import com.patika.bloghubuserservice.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .bio(user.getBio())
                .userType(user.getUserType())
                .build();
    }

    public static List<UserResponse> toResponse(List<User> users) {
        return users.stream().map(UserConverter::toResponse).toList();
    }
}
