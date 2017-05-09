package com.app.blog.services;

import com.app.blog.models.Post;

import java.util.List;

/**
 * Created by bacon_lover on 09/05/17.
 */
public interface PostService {

    List<Post> findAll();
    List<Post> findLatest5();
    Post findById(Long id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
}
