package com.samples.manytomany.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by edara on 9/14/16.
 */

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long author_id;
    private String author_name;
    @ManyToMany(mappedBy = "authors",cascade={CascadeType.PERSIST})
    private Set<Book> books = new HashSet<Book>();

    public Author(){}
    public Author(String name){
        this.author_name = name;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    public void addBook(Book book){
        getBooks().add(book);
        book.getAuthors().add(this);
    }
    public void removeBook(Book book){
        books.remove(book);
        book.getAuthors().remove(this);
    }
    public void removeBooks(){
        Iterator bookIterator = books.iterator();
        while(bookIterator.hasNext()){
            Book b =(Book) bookIterator.next();
            b.getAuthors().remove(this);
            bookIterator.remove();
        }
    }
}
