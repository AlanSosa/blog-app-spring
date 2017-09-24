package com.app.blog.models;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by bacon_lover on 08/05/17.
 */
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @Column(nullable = false, length = 300)
    private final String title;

    @Lob @Column(nullable = false)
    private final String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private final User author;

    @Column(nullable = false)
    private final Date date;

    public Post() {
        id = 0;
        title = "";
        body = "";
        author = null;
        date = new Date();
    }

    public Post(long id, String title, String body, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        date = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getBody() {
        String newBody = body.replace("\n", "<br/>");
        // TODO : cada post empiesa con con dos <br/> el 12 es para quitarlos corregir eso.
        return newBody.substring(12, newBody.length());
    }

    public User getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("Post [id=%d, title='%s', author='%s']", id, title, author.getUsername());
    }
}
