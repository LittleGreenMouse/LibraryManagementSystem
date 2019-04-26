package service;

import java.io.Serializable;

public class Book implements Serializable {

    // ISBN of book
    final private String isbn;
    // name of book
    final private String name;
    // author of book
    final private String author;

    /**
     * constructor
     * @param isbn ISBN
     * @param name name
     * @param author author
     */
    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }

    /**
     * getter for isbn
     * @return isbn
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for author
     * @return author
     */
    public String getAuthor() {
        return author;
    }
}

