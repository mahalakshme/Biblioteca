package com.twu.biblioteca;

/**
 * Created by mahalaks on 12/01/15.
 */
class Book extends Item{

    private String accessionNo;
    private String name;
    private String author;
    private String yearPublished;
    private boolean isAvailable;

    public Book(String accessionNo, String name, String author, String yearPublished, boolean isAvailable)
    {
        this.accessionNo = accessionNo;
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
    }

    public String getAccessionNo() {
        return accessionNo;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public boolean getIsAvailable()
    {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
