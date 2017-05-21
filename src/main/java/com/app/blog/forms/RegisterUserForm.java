package com.app.blog.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by bacon_lover on 20/05/17.
 */
public class RegisterUserForm {

    @NotNull
    @Size(min=2, max=30, message="Username size should be in range [2...30]")
    private String username;

    @NotNull
    @Size(min=1, max=5)
    private String password;

    @Size(min=2, max=100, message="Username size should be in range [2...30]")
    private String fullname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}

