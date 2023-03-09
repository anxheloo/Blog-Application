package com.blogExample.springbootblogapplication.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="account_authority",
    joinColumns = @JoinColumn(name="account_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="authority_name",referencedColumnName = "name"))
    private Set<Authority> authorities= new HashSet<>();


    public Account(String firstName, String password, String email, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
