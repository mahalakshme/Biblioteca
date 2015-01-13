package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by mahalaks on 13/01/15.
 */
public class BookTest {

    ArrayList<Book> books = new ArrayList<Book>();

    @Test
    public void CreateBookList()
    {
        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));
    }
}
