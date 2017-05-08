package com.app.blog.services;

import com.app.blog.models.Post;
import java.util.List;
/**
 * Created by bacon_lover on 07/05/17.
 */
public interface PostService {
    public List<Post> findAll();
    public List<Post> findLatest5();
    public Post findById(Long id);
    public Post create(Post post);
    public Post edit(Post post);
    public void deleteById(Long id);
}
