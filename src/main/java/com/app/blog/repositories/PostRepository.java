package com.app.blog.repositories;

import com.app.blog.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yuuko on 5/17/17.
 *
 * La annotation al menos en UserRepository primero la cale sin @Repository, despues vi que segun se
 * le tiene que agregar, asi que por si las moscas, se lo agregué.
 *
 * De ambas formas funcionó sin pedos....
 */

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
