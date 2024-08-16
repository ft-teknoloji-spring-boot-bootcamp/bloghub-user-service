package com.patika.bloghubuserservice.converter;

import com.patika.bloghubuserservice.dto.response.CustomUserDetail;
import com.patika.bloghubuserservice.model.User;

public class UserDetailConverter {
    public static CustomUserDetail toUserDetail(User user) {
        return new CustomUserDetail(user);
    }
}
