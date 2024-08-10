package com.patika.bloghubuserservice.model;

import com.patika.bloghubuserservice.model.enums.StatusType;
import com.patika.bloghubuserservice.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String bio;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType statusType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
