package com.twu.biblioteca;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mahalaks on 13/01/15.
 */
public class Session {

    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    private User user;

    public Session(User user) {

        this.user = user;
    }

    void CheckoutBook(String accessionNo, ArrayList<Book> books) {

        boolean success = removeBookWithAccessionNo(accessionNo, books);

        if(success) {
            System.out.println("Thank you! Enjoy the book");

        }
        else
        {
            System.out.println("That book is not available.");
        }
    }

    private boolean removeBookWithAccessionNo(String accessionNo, ArrayList<Book> books) {
        boolean isAvailable = false;
        for (Book book : books) {
            if(book.getAccessionNo().equals(accessionNo) && (book.getIsAvailable()))
            {
                book.setAvailable(false);
                isAvailable = true;
                this.getUser().getCheckedoutItems().add(book);
                return isAvailable;

            }

            if(book.getAccessionNo().equals(accessionNo) && !(book.getIsAvailable()))
            {
                return isAvailable;
            }
        }

        return isAvailable;
    }

    void Return(String accessionNo, ArrayList<Book> books) {

        boolean success = addBookWithAccessionNo(accessionNo, books);

        if(success) {
            System.out.println(successfulCheckoutMessage);
        }
        else
        {
            System.out.println(unsuccessfulCheckoutMessage);
        }
    }

    private boolean addBookWithAccessionNo(String accessionNo, ArrayList<Book> books) {
        for (Book book : books) {
            if((book.getAccessionNo().equals(accessionNo)) && (!book.getIsAvailable()))
            {
                book.setAvailable(true);
                this.getUser().getCheckedoutItems().remove(book);
                return true;
            }

            if((book.getAccessionNo().equals(accessionNo)) && (book.getIsAvailable()))
            {
                return false;
            }
        }

        return false;
    }

    public void CheckoutMovie(String accessionNo, ArrayList<Movie> movies) {

        boolean success = removeMovieWithIdNo(accessionNo, movies);

        if(success) {
            System.out.println(successfulCheckoutMessage);
        }
        else
        {
            System.out.println(unsuccessfulCheckoutMessage);
        }

    }

    private boolean removeMovieWithIdNo(String accessionNo, ArrayList<Movie> movies) {

        boolean isAvailable = false;
        for (Movie movie : movies) {
            if(movie.getId().equals(accessionNo) && (movie.getIsAvailable()))
            {
                movie.setIsAvailable(false);
                isAvailable = true;
                this.getUser().getCheckedoutItems().remove(movie);
                return isAvailable;
            }

            if(movie.getId().equals(accessionNo) && !(movie.getIsAvailable()))
            {
                return isAvailable;
            }
        }

        return isAvailable;
    }

    public void ExecuteAction(InputStream inContent, char choice, ArrayList<Book> books, ArrayList<Movie> movies) {
        String accessionNo;
        Scanner input = new Scanner(inContent);
            switch(choice)
            {
                case '2':
                    System.out.println("Enter the accession no of the book to be checked out:");
                    accessionNo = input.next();
                    CheckoutBook(accessionNo, books);
                    break;
                case '3':
                    System.out.println("Enter the accession no of the book to be returned:");
                    accessionNo = input.next();
                    Return(accessionNo, books);
                    break;
                case '5' :
                    System.out.println("Enter the id no of the movie to be checked out:");
                    accessionNo = input.next();
                    CheckoutMovie(accessionNo, movies);
            }
    }

    public User getUser() {
        return user;
    }
}
