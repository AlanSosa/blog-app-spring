package com.app.blog.repositories;

import com.app.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bacon_lover on 20/05/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
