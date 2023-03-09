package com.blogExample.springbootblogapplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
//@Data  Get method of /posts/new, not working with this
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor   //try using only @Data in the end
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //try GenerationType.IDENTITY
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")  //try Different usage of @Column : @Column(name = "TEXT")
    private String body;

    private LocalDateTime createdAt;


    @NotNull
    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName = "id",nullable = false)
    private Account account;


    private LocalDateTime updatedAt;

    //the guy in the video use setter method for creating user so i personally created this constructor to handle this myself
    public Post(String title, String body, Account account) {
        this.title = title;
        this.body = body;
        this.account = account;
    }

}
