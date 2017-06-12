package com.app.blog.repositories;

import com.app.blog.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bacon_lover on 24/05/17.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
