package com.patika.bloghubuserservice.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSaveRequest {

    private String name;
    private String email;
    private String password;

}
