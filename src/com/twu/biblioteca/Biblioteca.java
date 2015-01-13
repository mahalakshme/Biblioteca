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

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(System.in);

    }

    public Biblioteca(InputStream inContent) {

        bangalorePublicLibrary = new Library();
        menu = new Menu();

        System.out.print("Welcome");
        System.out.println();

        ChooseOption(inContent);

    }


    private void ChooseOption(InputStream inContent) {

        String option;
        String shouldContinue = "Y";
        Scanner input = new Scanner(inContent);

            while(shouldContinue.charAt(0) == 'Y')
            {
                menu.DisplayMenu();
                System.out.println("Enter q to Quit");
                option = input.next();
             switch (option.charAt(0))
             {
                 case '1':
                     bangalorePublicLibrary.DisplayBooks();
                     break;

                 case '2':
                 case '3':
                 case '5':
                     EnsureIfUserIsLoggedIn(inContent, option.charAt(0));
                     break;

                 case '4':
                     bangalorePublicLibrary.DisplayMovies();
                     break;

                 case 'q':
                     return;

                 default:
                     System.out.println("Select a valid option!");

             }
                System.out.println("Do you want to continue?<Y/N>");
                shouldContinue = input.next();
        }
    }

    private void RequestUserToLogin(InputStream inContent, char choice) {
        Scanner input = new Scanner(inContent);
        System.out.println("enter user id and password:");

        String userId = input.next();
        String password = input.next();
        boolean loggedin =  false;

        for (User user : bangalorePublicLibrary.users) {
            if(user.getId().equals(userId) && user.getPassword().equals(password))
            {
                bangalorePublicLibrary.session = new Session(user);
                loggedin = true;
                bangalorePublicLibrary.session.ExecuteAction(inContent, choice, bangalorePublicLibrary.books, bangalorePublicLibrary.movies);
                break;
            }

            if(user.getId().equals(userId) && (!user.getPassword().equals(password)))
            {
                break;
            }
        }

        if(!loggedin)
        {
            System.out.println("Invalid credentials");
        }
    }

    private void EnsureIfUserIsLoggedIn(InputStream inContent,char choice) {

        if(bangalorePublicLibrary.session != null)
        {
            bangalorePublicLibrary.session.ExecuteAction(inContent, choice, bangalorePublicLibrary.books, bangalorePublicLibrary.movies);
        }
        else {
            RequestUserToLogin(inContent, choice);
        }
    }

    public static void main(String[] args)  {
        new Biblioteca();
    }
}
