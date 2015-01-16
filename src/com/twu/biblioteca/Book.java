package com.twu.biblioteca;

/**
 * Created by mahalaks on 12/01/15.
 */
class Book extends Item {

    private String author;
    private String yearPublished;

    public Book(String accessionNo, String name, String author, String yearPublished, boolean isAvailable) {
        this.accessionNo = accessionNo;
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
    }


    String getAuthor() {
        return author;
    }

    String getYearPublished() {
        return yearPublished;
    }

}