package com.app.blog.repositories;

import com.app.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by yuuko on 5/17/17.
 */
public interface PostRepository extends CrudRepository<Post, Long> {

}
