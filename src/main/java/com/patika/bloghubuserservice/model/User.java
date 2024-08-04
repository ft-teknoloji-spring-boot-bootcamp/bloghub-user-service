package com.patika.bloghubuserservice.model;


import com.patika.bloghubuserservice.model.enums.StatusType;
import com.patika.bloghubuserservice.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UserType userType;
    private String bio;
    private StatusType statusType;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //private Set<SocialMedia> socialMediaList;

    //private List<BlogTag> followedTagList = new ArrayList<>();


}
