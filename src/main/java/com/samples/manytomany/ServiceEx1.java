package com.samples.manytomany;

import com.samples.manytomany.model.Author;
import com.samples.manytomany.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by edara on 9/14/16.
 */
@Component
public class ServiceEx1 {
    @Autowired
    EntityManagerFactory emf;

    public void run() {
        System.out.println("Method-1 *******************************************");
        saveAuthor();
        System.out.println("Method-2 *******************************************");
        //disassociateBooktoAuthor();
        deleteAuthor();
    }

    private void saveAuthor() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author12 = new Author("author12");
        Book book1 = new Book("book1");
        Book book2 = new Book("book2");
        Author author23 = new Author("author23");
        Book book3 = new Book("book3");
        author12.addBook(book1);
        author12.addBook(book2);
        author23.addBook(book2);
        author23.addBook(book3);
        entityManager.persist(author12);
        entityManager.persist(author23);
        transaction.commit();
    }

    private void disassociateBooktoAuthor() {
        String getAuthorSQL = "select a from Author a where a.author_name= :authorName ";
        String getBookSQL = "select b from Book b where b.book_title= :title";

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author = entityManager.createQuery(getAuthorSQL,Author.class).setParameter("authorName", "author23").getSingleResult();
        System.out.println(author.getAuthor_id()+"**************"+author.getAuthor_name());
        Book book = entityManager.createQuery(getBookSQL,Book.class).setParameter("title","book2").getSingleResult();
        author.removeBook(book);
        transaction.commit();
    }

    private void deleteAuthor(){
        String getAuthorSQL = "select a from Author a where a.author_name= :authorName ";
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author = entityManager.createQuery(getAuthorSQL,Author.class).setParameter("authorName", "author23").getSingleResult();
        System.out.println(author.getAuthor_id()+"**************"+author.getAuthor_name());
        author.removeBooks();
        entityManager.remove(author);
        transaction.commit();

    }
}
