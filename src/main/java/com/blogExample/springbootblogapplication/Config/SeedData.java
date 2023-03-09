package com.blogExample.springbootblogapplication.Config;

import com.blogExample.springbootblogapplication.Repository.AuthorityRepository;
import com.blogExample.springbootblogapplication.Services.AccountService;
import com.blogExample.springbootblogapplication.Services.PostService;
import com.blogExample.springbootblogapplication.models.Account;
import com.blogExample.springbootblogapplication.models.Authority;
import com.blogExample.springbootblogapplication.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;


    @Autowired
    AuthorityRepository authorityRepository;


    @Override
    public void run(String... args) throws Exception {

        List<Post> posts = postService.getAll();

        if(posts.size() == 0 )
        {

            Authority user = new Authority("ROLE_USER");
            Authority admin = new Authority("ROLE_ADMIN");
            authorityRepository.save(user);
            authorityRepository.save(admin);

            Account account1 = new Account("user","password","user@gmail.com","user");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            Account account2 = new Account("admin","password","admin@gmail.com","admin");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            accountService.save(account1);
            accountService.save(account2);

            Post post1 = new Post("title of post 1","This is the body of post 1",account1);
            Post post2 = new Post("title of post 2","This is the body of post 2",account2);

            postService.save(post1);
            postService.save(post2);
        }



    }
}
