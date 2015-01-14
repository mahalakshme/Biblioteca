package com.twu.biblioteca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mahalaks on 12/01/15.
 */
public class Library {

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<User> users = new ArrayList<User>();
    User loggedinUser;

    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    String successfulReturnMessage = "Thank you for returning";
    String unsuccessfulReturnMessage = "That is not a valid item to return.";

    public Library()
    {
        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));

        movies.add(new Movie("104", "Angels and Demons", "1999", "Dan Brown", 5, true));
        movies.add(new Movie("105", "Davinci Code", "1990", "Michael", 4, false));
        movies.add(new Movie("106", "harry potter", "1999", "carry Lounge", 3, true));

        users.add(new User("123-1234", "1009", "maha", "mahalakshme@gmail.com", "9600252894"));
        users.add(new User("123-1235", "1006", "mawa", "mawalakshme@gmail.com", "9600262894"));
        users.add(new User("123-1236", "1007", "mara", "maralakshme@gmail.com", "9600272894"));
    }

    void DisplayBooks() {

        for (Book book : books) {
            System.out.print(book.getAccessionNo());
            System.out.print("\t");
            System.out.print(book.getName());
            System.out.print("\t");
            System.out.print(book.getAuthor());
            System.out.print("\t");
            System.out.println(book.getYearPublished());
        }
    }

    void CheckoutBook(String accessionNo) {

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
            if(book.getAccessionNo().equals(accessionNo) && (book.getIsAvailable()))
            {
                book.setAvailable(false);
                isAvailable = true;
                this.loggedinUser.setCheckedoutItems(book);
                return isAvailable;

            }

            if(book.getAccessionNo().equals(accessionNo) && !(book.getIsAvailable()))
            {
                return isAvailable;
            }
        }

        return isAvailable;
    }

    void Return(String accessionNo) {

        boolean success = addBookWithAccessionNo(accessionNo);

        if(success) {
            System.out.println(successfulReturnMessage);
        }
        else
        {
            System.out.println(unsuccessfulReturnMessage);
        }
    }

    private boolean addBookWithAccessionNo(String accessionNo) {
        for (Book book : books) {
            if((book.getAccessionNo().equals(accessionNo)) && (!book.getIsAvailable()))
            {
                book.setAvailable(true);
                this.loggedinUser.getCheckedoutItems().remove(book);
                return true;
            }

            if((book.getAccessionNo().equals(accessionNo)) && (book.getIsAvailable()))
            {
                return false;
            }
        }

        return false;
    }

    public void CheckoutMovie(String accessionNo) {

        boolean success = removeMovieWithIdNo(accessionNo);

        if(success) {
            System.out.println(successfulCheckoutMessage);
        }
        else
        {
            System.out.println(unsuccessfulCheckoutMessage);
        }

    }

    private boolean removeMovieWithIdNo(String accessionNo) {

        boolean isAvailable = false;
        for (Movie movie : movies) {
            if(movie.getId().equals(accessionNo) && (movie.getIsAvailable()))
            {
                movie.setIsAvailable(false);
                isAvailable = true;
                this.loggedinUser.setCheckedoutItems(movie);
                return isAvailable;
            }

            if(movie.getId().equals(accessionNo) && !(movie.getIsAvailable()))
            {
                return isAvailable;
            }
        }

        return isAvailable;
    }


    void DisplayMovies() {

        for (Movie movie : movies) {
            if(movie.getIsAvailable()) {
                System.out.print(movie.getId());
                System.out.print("\t");
                System.out.print(movie.getName());
                System.out.print("\t");
                System.out.print(movie.getDirector());
                System.out.print("\t");
                System.out.print(movie.getYear());
                System.out.print("\t");
                System.out.println(movie.getRating());
            }
        }

    }

}
