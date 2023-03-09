package com.blogExample.springbootblogapplication.Services;

import com.blogExample.springbootblogapplication.Repository.PostRepository;
import com.blogExample.springbootblogapplication.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Optional<Post> getById(Long id )
    {
        return postRepository.findById(id);
    }

    public List<Post> getAll()
    {
        return postRepository.findAll();
    }

    public Post save(Post post)
    {
        if(post.getId()==null)
        {
            post.setCreatedAt(LocalDateTime.now());
        }

        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public void delete(Post post)
    {
        postRepository.delete(post);
    }

}
