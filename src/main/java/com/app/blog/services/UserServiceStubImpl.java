package com.app.blog.services;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by bacon_lover on 12/05/17.
 */
@Service
public class UserServiceStubImpl implements UserService{
    @Override
    public boolean aunthenticate(String username, String password) {
        return Objects.equals(username, password);
    }
}
