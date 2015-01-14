package com.twu.biblioteca;

import java.io.*;
import java.util.Scanner;

/**
 * Created by mahalaks on 10/01/15.
 */
public class Biblioteca {

    Library bangalorePublicLibrary;
    Menu menu;

    public Biblioteca() {

        bangalorePublicLibrary = new Library();
        menu = new Menu();
        CreateMenu();

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(System.in);

    }

    public Biblioteca(InputStream inContent) {

        bangalorePublicLibrary = new Library();
        menu = new Menu();
        CreateMenu();

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(inContent);

    }

    void CreateMenu() {
        menu.options.add("1.List Books");
        menu.options.add("2.Checkout Book");
        menu.options.add("3.Return Book");
        menu.options.add("4.List Movies");
    }

    void DisplayMenu() {
        for (String option : menu.options) {
            System.out.print(option + "\t");
        }

        System.out.println();
    }

    private void ChooseOption(InputStream inContent) {
        String accessionNo;
        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);

        while (shouldContinue.charAt(0) == 'Y') {
            DisplayMenu();
            System.out.println("Enter q to Quit");
            option = input.next();

            switch (option.charAt(0)) {
                case '1':
                    bangalorePublicLibrary.DisplayBooks();
                    break;

                case '2':
                    if(EnsureIfUserIsLoggedin(input)) {
                        System.out.println("Enter the accession no of the book to be checked out:");
                        accessionNo = input.next();
                        bangalorePublicLibrary.CheckoutBook(accessionNo);
                    }
                    break;
                case '3':
                    if(EnsureIfUserIsLoggedin(input)) {
                        System.out.println("Enter the accession no of the book to be returned:");
                        accessionNo = input.next();
                        bangalorePublicLibrary.Return(accessionNo);
                    }
                    break;

                case '4':
                    bangalorePublicLibrary.DisplayMovies();
                    break;

                case '5' :
                    if(EnsureIfUserIsLoggedin(input)) {
                        System.out.println("Enter the id no of the movie to be checked out:");
                        accessionNo = input.next();
                        bangalorePublicLibrary.CheckoutMovie(accessionNo);
                    }

                case 'q':
                    return;

                default:
                    System.out.println("Select a valid option!");

            }
            System.out.println("Do you want to continue?<Y/N>");
            shouldContinue = input.next();
        }
    }

    private boolean RequestUserToLogin(Scanner input) {
        System.out.println("enter user id and password:");

        String userId = input.next();
        String password = input.next();

        for (User user : bangalorePublicLibrary.users) {
            if (user.getId().equals(userId) && user.getPassword().equals(password)) {
                 bangalorePublicLibrary.loggedinUser = user;
                return true;
            }

            if (user.getId().equals(userId) && (!user.getPassword().equals(password))) {
                break;
            }
        }
            System.out.println("Invalid credentials");
            return false;
        }

    private boolean EnsureIfUserIsLoggedin(Scanner input) {

        if (bangalorePublicLibrary.loggedinUser != null) {
            return true;
        } else {
            return RequestUserToLogin(input);
        }
    }

    public static void main(String[] args) {
         new Biblioteca();
    }
}
