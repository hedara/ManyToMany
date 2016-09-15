package com.samples.manytomany.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by edara on 9/14/16.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long book_id;
    private String book_title;
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="author_id",nullable = false,updatable = false)}
    )
    private Set<Author> authors = new HashSet<Author>();

    public Book(){
    }
    public Book(String title) {
        this.book_title = title;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    public void removeAuthor(Author author) {
        authors.remove(author);
    }
}
