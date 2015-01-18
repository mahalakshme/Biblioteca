package com.twu.biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mahalaks on 10/01/15.
 */

public class Biblioteca {

    BibliotecaInterface bibliotecaInterface;
    Menu menu;
    UserManager userManager;
    Scanner input;
    PrintStream outContent;

    public Biblioteca(Scanner inContent, PrintStream outContent, BibliotecaInterface bibliotecaInterface, UserManager userManager) {

        this.menu = new Menu(bibliotecaInterface, userManager);
        this.bibliotecaInterface = bibliotecaInterface;
        this.userManager = userManager;
        this.input = inContent;
        this.outContent = outContent;
        outContent.println("Welcome");

        InputOption();
    }

    void DisplayMenu() {

        for (Option option : menu.GetOptions()) {
            outContent.print(option.getDisplayName() + "\t");
        }

        outContent.println();
    }

    private void InputOption() {

        String option;
        String shouldContinue = "Y";

        while (shouldContinue.charAt(0) == 'Y') {
            DisplayMenu();
            outContent.println("Enter q to Quit");
            option = input.next();

            if(!menu.ChooseOption((int)option.charAt(0) - '0'))
            {
                outContent.println("Select a valid option!");
            }

            outContent.println("Do you want to continue?<Y/N>");
            shouldContinue = input.next();
        }
    }


    public static void main(String[] args) {

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
        users.add(new User(new Credential("111-1111", "1000"), "king", "king@gmail.com", "9791213456"));

        UserManager userManager = new UserManager(users);

        final Library publicLibrary = new Library(books, movies, userManager);

        Scanner input = new Scanner(System.in);
        BibliotecaInterface bibliotecaInterface = new BibliotecaInterface(publicLibrary, input, System.out);
        new Biblioteca(input, System.out, bibliotecaInterface, userManager);
    }
}
