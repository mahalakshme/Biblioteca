package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mahalaks on 10/01/15.
 */

public class Biblioteca {

    BibliotecaInterface bibliotecaInterface;

    String invalidOptionMessage = "Select a valid option!";
    String invalidCredentialsMessage = "Invalid credentials";

    public Biblioteca(InputStream inContent, PrintStream outContent, BibliotecaInterface bibliotecaInterface) {

        this.bibliotecaInterface = bibliotecaInterface;
        outContent.println("Welcome");

        ChooseOption(inContent, outContent);
    }

    private void DisplayMenu(PrintStream outContent) {

        for (String option : bibliotecaInterface.menu.options) {
            outContent.print(option + "\t");
        }

        outContent.println();
    }

    private void DisplayBooks(PrintStream outContent) {

        for (Book book : bibliotecaInterface.bangalorePublicLibrary.books) {
            outContent.print(book.getAccessionNo());
            outContent.print("\t");
            outContent.print(book.getName());
            outContent.print("\t");
            outContent.print(book.getAuthor());
            outContent.print("\t");
            outContent.println(book.getYearPublished());
        }
    }


    private void ChooseOption(InputStream inContent, PrintStream outContent) {

        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);

        while (shouldContinue.charAt(0) == 'Y') {
            DisplayMenu(outContent);
            outContent.println("Enter q to Quit");
            option = input.next();

            switch (option.charAt(0)) {
                case '1':
                    DisplayBooks(outContent);
                    break;

                case '2':
                    if(bibliotecaInterface.IsUserLoggedin()) {
                        InitiateBookCheckout(input, outContent);
                    }
                    else
                    {
                        Credential credential = RequestUserCredentials(input, outContent);
                        if(bibliotecaInterface.AreUserCredentialsValid(credential))
                        {
                            InitiateBookCheckout(input, outContent);
                        }
                        else
                        {
                            outContent.println(invalidCredentialsMessage);
                        }
                    }
                    break;

                case '3':
                    if(bibliotecaInterface.IsUserLoggedin()) {
                        InitiateBookReturn(input, outContent);
                    }
                    else
                    {
                        Credential credential = RequestUserCredentials(input, outContent);
                        if(bibliotecaInterface.AreUserCredentialsValid(credential))
                        {
                            InitiateBookReturn(input, outContent);
                        }
                        else
                        {
                            outContent.println(invalidCredentialsMessage);
                        }
                    }
                    break;

                case '4':
                    DisplayMovies(outContent);
                    break;

                case '5' :
                    if(bibliotecaInterface.IsUserLoggedin()) {
                        InitiateMovieCheckout(input, outContent);
                    }
                    else
                    {
                        Credential credential = RequestUserCredentials(input, outContent);
                        if(bibliotecaInterface.AreUserCredentialsValid(credential))
                        {
                            InitiateMovieCheckout(input, outContent);
                        }
                        else
                        {
                            outContent.println(invalidCredentialsMessage);
                        }

                    }
                    break;

                case '6' :
                    if(bibliotecaInterface.IsUserLoggedin())
                    {
                        DiplayLoggedinUserInfo(outContent);
                    }
                    else
                    {
                        outContent.println(invalidOptionMessage);
                    }
                    break;

                case '7':
                    if(bibliotecaInterface.IsUserLoggedin() && bibliotecaInterface.isLoggedinUserLibrarian())
                    {
                        outContent.println("Enter accession no:");
                        String accessionNo = input.next();
                        outContent.println(bibliotecaInterface.FindUserWhoHasCheckedoutBook(accessionNo));
                    }
                    else
                    {
                        outContent.println(invalidOptionMessage);
                    }
                    break;

                case '8':
                    bibliotecaInterface.Logout();
                    outContent.println("You are loggedout");
                    break;

                case '9':
                    Credential credential = RequestUserCredentials(input, outContent);
                    if(bibliotecaInterface.AreUserCredentialsValid(credential))
                    {
                        outContent.println("You are loggedin");
                    }

                    break;

                case 'q':
                    return;

                default:
                    outContent.println(invalidOptionMessage);

            }

            outContent.println("Do you want to continue?<Y/N>");
            shouldContinue = input.next();
        }
    }

    private void InitiateMovieCheckout(Scanner input, PrintStream outContent) {
        String accessionNo;
        outContent.println("Enter the id no of the movie to be checked out:");
        accessionNo = input.next();
        outContent.println(bibliotecaInterface.CheckoutMovie(accessionNo));
    }

    private void InitiateBookReturn(Scanner input, PrintStream outContent) {
        String accessionNo;
        outContent.println("Enter the accession no of the book to be returned:");
        accessionNo = input.next();
        outContent.println(bibliotecaInterface.ReturnBook(accessionNo));
    }

    private void InitiateBookCheckout(Scanner input, PrintStream outContent) {
        String accessionNo;
        outContent.println("Enter the accession no of the book to be checked out:");
        accessionNo = input.next();
        outContent.println(bibliotecaInterface.CheckoutBook(accessionNo));
    }

    private Credential RequestUserCredentials(Scanner input, PrintStream outContent) {
        outContent.println("enter user id and password:");

        String userId = input.next();
        String password = input.next();
        return new Credential(userId, password);
    }

    private void DisplayMovies(PrintStream outContent) {

            for (Movie movie : bibliotecaInterface.bangalorePublicLibrary.movies) {
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

    private void DiplayLoggedinUserInfo(PrintStream outContent) {
            outContent.println("Name:" + bibliotecaInterface.loggedinUser.getName());
            outContent.println("Email address:" + bibliotecaInterface.loggedinUser.getEmailId());
            outContent.println("Phone no:" + bibliotecaInterface.loggedinUser.getPhoneNo());
        }


    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.options.add("1.List Books");
        menu.options.add("2.Checkout Book");
        menu.options.add("3.Return Book");
        menu.options.add("4.List Movies");
        menu.options.add("5.Checkout Movie");
        menu.options.add("9.Login");

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("100", "Angels and Demons", "Dan Brown", "1999", true));
        books.add(new Book("101", "Davinci Code", "Michael", "1990", false));
        books.add(new Book("103", "harry potter", "carry Lounge", "1999", true));

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("104", "Angels and Demons", "1999", "Dan Brown", 5, true));
        movies.add(new Movie("105", "Davinci Code", "1990", "Michael", 4, false));
        movies.add(new Movie("106", "harry potter", "1999", "carry Lounge", 3, true));

        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(new Credential("123-1234", "1009"), "maha", "mahalakshme@gmail.com", "9600252894"));
        users.add(new User(new Credential("123-1235", "1006"), "mawa", "mawalakshme@gmail.com", "9600262894"));
        users.add(new User(new Credential("123-1236", "1007"), "mara", "maralakshme@gmail.com", "9600272894"));
        users.add(new Librarian(new Credential("111-1111", "1000"), "king", "king@gmail.com", "9791213456"));

        final Library publicLibrary = new Library(books, movies, users);
        BibliotecaInterface bibliotecaInterface = new BibliotecaInterface(menu, publicLibrary);
        new Biblioteca(System.in, System.out, bibliotecaInterface);
    }
}
