package com.app.blog.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bacon_lover on 08/05/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(length = 60)
    private String password;

    @Column(length = 100)
    private String fullName;

    @Column(nullable = false)
    @Max(value = 1)
    private int enabled;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<Post>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getEnabled() { return enabled; }

    public void setEnabled(int enabled) { this.enabled = enabled; }

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public User() {
    }

    public User(Long id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.enabled = 1;
    }

    public User(String username, String password, String fullName, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.enabled = 1;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", posts= + posts" +
                '}';
    }
}
