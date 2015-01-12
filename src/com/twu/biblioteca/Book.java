package com.twu.biblioteca;

/**
 * Created by mahalaks on 12/01/15.
 */
class Book {

    String accessionNo;
    String name;
    String author;
    String yearPublished;
    boolean bookAvailable;

    public Book(String accessionNo, String name, String author, String yearPublished, boolean bookAvailable)
    {
        this.accessionNo = accessionNo;
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
        this.bookAvailable = bookAvailable;
    }
}
