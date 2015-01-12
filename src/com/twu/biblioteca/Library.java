package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by mahalaks on 12/01/15.
 */
public class Library {

    ArrayList<Book> books = new ArrayList<Book>();

    public Library()
    {
        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));
    }

    void DisplayBooks() {

        for (Book book : books) {
            System.out.print(book.accessionNo);
            System.out.print("\t");
            System.out.print(book.name);
            System.out.print("\t");
            System.out.print(book.author);
            System.out.print("\t");
            System.out.println(book.yearPublished);
        }
    }

    void Checkout(String accessionNo) {

       boolean success = removeBookWithAccessionNo(accessionNo);

       if(success) {
            System.out.println("Thank you! Enjoy the book");
        }
        else
       {
            System.out.println("That book is not available.");
        }
    }

    private boolean removeBookWithAccessionNo(String accessionNo) {
        boolean isAvailable = false;
        for (Book book : books) {
            if(book.accessionNo.equals(accessionNo) && (book.bookAvailable))
            {
                book.bookAvailable = false;
                isAvailable = true;
                return isAvailable;
            }

            if(book.accessionNo.equals(accessionNo) && !(book.bookAvailable))
            {
               return isAvailable;
            }
        }

        return isAvailable;
        }

    void Return(String accessionNo) {

        boolean success = addBookWithAccessionNo(accessionNo);

        if(success) {
            System.out.println("Thank you for returning the book.");
        }
        else
        {
            System.out.println("That is not a valid book to return.");
        }
    }

    private boolean addBookWithAccessionNo(String accessionNo) {
        for (Book book : books) {
            if((book.accessionNo.equals(accessionNo)) && (!book.bookAvailable))
            {
                book.bookAvailable = true;
                return true;
            }

            if((book.accessionNo.equals(accessionNo)) && (book.bookAvailable))
            {
                return false;
            }
        }

        return false;
    }
}
