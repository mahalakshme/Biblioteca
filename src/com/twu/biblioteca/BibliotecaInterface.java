package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by mahalaks on 15/01/15.
 */
public class BibliotecaInterface {

    String successfulCheckoutMessage = "Thank you! Enjoy";
    String unsuccessfulCheckoutMessage = "That item is not available.";
    String successfulReturnMessage = "Thank you for returning";
    String unsuccessfulReturnMessage = "That is not a valid item to return.";
    String findingUserUnsuccessfulMessage = "Book is not checkedout or entered AccessionNo not valid.";

    Library bangalorePublicLibrary;
    Scanner input;
    PrintStream outContent;

    public BibliotecaInterface(Library publicLibrary, Scanner inContent, PrintStream outContent)
    {
        bangalorePublicLibrary = publicLibrary;
        this.input = inContent;
        this.outContent = outContent;
    }

    void DisplayBooks() {

        for (Book book : bangalorePublicLibrary.getBooks()) {

            outContent.print(book.getAccessionNo());
            outContent.print("\t");
            outContent.print(book.getName());
            outContent.print("\t");
            outContent.print(book.getAuthor());
            outContent.print("\t");
            outContent.println(book.getYearPublished());
        }
    }

    void PrintMessage(Message message)
    {
        outContent.println(message.getMessage());
    }

    void FindUserWhoHasCheckedoutBook() {

        outContent.println("Enter accession no:");
        String accessionNo = input.next();

        String userInfo = bangalorePublicLibrary.FindUserWhoHasCheckedoutBook(accessionNo);
        if(userInfo != null)
        {
            outContent.println(userInfo);
        }
        else
        {
            outContent.println(findingUserUnsuccessfulMessage);
        }
    }

    void DisplayMovies() {

            for (Movie movie : bangalorePublicLibrary.getMovies()) {
                if(movie.getIsAvailable()) {
                    outContent.print(movie.getAccessionNo());
                    outContent.print("\t");
                    outContent.print(movie.getName());
                    outContent.print("\t");
                    outContent.print(movie.getDirector());
                    outContent.print("\t");
                    outContent.print(movie.getYear());
                    outContent.print("\t");
                    outContent.println(movie.getRating());
                }
            }
        }

    void DiplayLoggedinUserInfo(User loggedinUser) {

            outContent.println("Name:" + loggedinUser.getName());
            outContent.println("Email address:" + loggedinUser.getEmailId());
            outContent.println("Phone no:" + loggedinUser.getPhoneNo());
        }

    void CheckoutMovie(User loggedinUser) {
        String accessionNo;
        outContent.println("Enter the id no of the movie to be checked out:");
        accessionNo = input.next();
        Movie movie = bangalorePublicLibrary.CheckoutMovie(accessionNo, loggedinUser);
        if(movie != null)
        {
            outContent.println(successfulCheckoutMessage);
        }
        else
        {
            outContent.println(unsuccessfulCheckoutMessage);
        }
    }

    void ReturnBook(User loggedinUser) {
        String accessionNo;
        outContent.println("Enter the accession no of the book to be returned:");
        accessionNo = input.next();

        if(bangalorePublicLibrary.Return(accessionNo, loggedinUser))
        {
            outContent.println(successfulReturnMessage);
        }
        else
        {
            outContent.println(unsuccessfulReturnMessage);
        }
    }

    void CheckoutBook(User loggedinUser) {
        String accessionNo;
        outContent.println("Enter the accession no of the book to be checked out:");
        accessionNo = input.next();
        Book book = bangalorePublicLibrary.CheckoutBook(accessionNo, loggedinUser);
        if(book != null)
        {
            outContent.println(successfulCheckoutMessage);
        }
        else
        {
            outContent.println(unsuccessfulCheckoutMessage);
        }
    }

    Credential RequestUserCredentials() {
        outContent.println("enter user id and password:");

        String userId = input.next();
        String password = input.next();
        return new Credential(userId, password);
    }
}
